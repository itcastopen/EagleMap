package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.mapper.TraceMapper;
import cn.itcast.em.mapper.TraceTerminalMapper;
import cn.itcast.em.pojo.Trace;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.TraceService;
import cn.itcast.em.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 百度地图的轨迹理实现
 */
@Service("BaiduTraceService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduTraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements TraceService {

    @Resource
    private EagleConfig eagleConfig;
    @Resource
    private BaiduWebApiService baiduWebApiService;
    @Resource
    private TraceTerminalMapper traceTerminalMapper;

    /**
     * 创建轨迹
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param name       轨迹名称
     * @return 轨迹id
     */
    @Override
    public String create(Long serverId, Long terminalId, String name) {
        //将服务数据存储到数据库

        //生成id
        long id = IdUtil.getSnowflake().nextId();
        Trace trace = new Trace();
        trace.setId(id);
        trace.setProvider(ServerType.BAIDU);
        trace.setServerId(serverId);
        trace.setName(name);
        trace.setTerminalId(terminalId);
        trace.setStatus(0);
        trace.setTraceId(id);
        trace.setStartTime(new Date());

        super.save(trace);

        return Convert.toStr(id);
    }

    @Override
    public String delete(Long serverId, Long terminalId, Long traceId) {
        return super.removeById(traceId) ? "ok" : "err";
    }

    @Override
    public String upload(Long serverId, Long terminalId, Long traceId, List<Map<String, Object>> pointList) {
        TraceTerminal traceTerminal = this.traceTerminalMapper.selectById(terminalId);

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/track/addpoints";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("point_list", JSONUtil.toJsonStr(pointList.stream()
                .peek(map -> map.put("entity_name", traceTerminal.getName()))
                .collect(Collectors.toList())));

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("status") != 0) {
                return body;
            }
            return "ok";
        });
    }

    /**
     * 停止运动，该方法会将轨迹中的轨迹点数据持久化本地数据库中
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param param      其他的请求参数
     * @return
     */
    @Override
    @Transactional
    public String stopTrace(Long serverId, Long terminalId, Long traceId, Map<String, Object> param) {
        //修改状态为停止运动
        Trace trace = super.getById(traceId);
        if (null == trace) {
            return null;
        }

        //查询轨迹详情数据
        this.queryTraceFromBaidu(trace, param);

        trace.setEndTime(new Date());
        trace.setStatus(1);
        //更新的条件
        super.updateById(trace);

        return "ok";
    }

    /**
     * 分页查询轨迹列表，按照轨迹创建时间倒序排序
     *
     * @param page     页数
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public PageResult<Trace> queryTracePageList(Integer page, Integer pageSize) {
        //分页构造器
        Page<Trace> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Trace> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Trace::getCreated);

        super.page(pageInfo, queryWrapper);

        return new PageResult().convert(pageInfo);
    }

    @Override
    public List<Trace> searchTraceList(Long traceId, String traceName) {
        LambdaQueryWrapper<Trace> queryWrapper = new LambdaQueryWrapper<>();
        if (null != traceId) {
            queryWrapper.eq(Trace::getTraceId, traceId);
        }
        if (StrUtil.isNotEmpty(traceName)) {
            queryWrapper.like(Trace::getName, traceName);
        }

        queryWrapper.eq(Trace::getProvider, ServerType.BAIDU);

        return super.list(queryWrapper);
    }

    /**
     * 查询轨迹详情，优先查询本地数据库，如本地库中没有数据，再查询地图服务商
     *
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param param      其他的请求参数
     * @return
     */
    @Override
    public Trace queryTraceInfo(Long serverId, Long terminalId, Long traceId, Map<String, Object> param) {
        //查询持久化的数据,先查询轨迹数据
        Trace trace = super.getById(traceId);
        if (null == trace) {
            return null;
        }

        if (ObjectUtil.equal(trace.getStatus(), 1) && CollUtil.isNotEmpty(param) &&
                Convert.toBool(param.get("local"), true)) {
            return trace;
        }

        //从百度地图中查询数据
        this.queryTraceFromBaidu(trace, param);
        return trace;
    }

    private void queryTraceFromBaidu(Trace trace, Map<String, Object> param) {
        TraceTerminal traceTerminal = this.traceTerminalMapper.selectById(trace.getTerminalId());
        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/track/gettrack";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        int pageSize = 5000;
        int page = 1;
        long startTime = trace.getStartTime().getTime() / 1000;
        long entTime = System.currentTimeMillis() / 1000;
        requestParam.put("service_id", trace.getServerId());
        requestParam.put("entity_name", traceTerminal.getName());
        requestParam.put("start_time", startTime);
        requestParam.put("end_time", entTime);
        requestParam.put("page_index", page);
        requestParam.put("page_size", pageSize);
        Trace traceInfo = executeQuery(url, requestParam);
        if (traceInfo.getSize() > pageSize) {
            //存在下一页数据，循环分页获取数据
            JSONArray jsonArray = JSONUtil.parseArray(traceInfo.getPointList());
            for (; ; ) {
                page++;
                requestParam.put("page_index", page);
                Trace tInfo = executeQuery(url, requestParam);
                JSONArray array = JSONUtil.parseArray(tInfo.getPointList());
                if (CollUtil.isEmpty(array)) {
                    break;
                }
                //将轨迹点数据整合到上面的对象中
                jsonArray.addAll(array);
            }

            //写回到对象中
            traceInfo.setPointList(JSONUtil.toJsonStr(jsonArray));
        }

        //拷贝数据到trace对象中
        BeanUtil.copyProperties(traceInfo, trace, CopyOptions.create().ignoreNullValue());
    }

    private Trace executeQuery(String url, Map<String, Object> requestParam) {
        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("status") != 0) {
                throw new RuntimeException(body);
            }

            Trace trace = new Trace();
            trace.setSize(json.getInt("total"));
            trace.setDistance(json.getDouble("distance"));
            trace.setPointList(Convert.toStr(json.getJSONArray("points")));

            JSONObject startJsonObj = json.getJSONObject("start_point");
            JSONObject endJsonObj = json.getJSONObject("end_point");

            //设置起始坐标信息
            trace.setStartPoint(startJsonObj.getStr("longitude") + "," + startJsonObj.getStr("latitude"));
            trace.setEndPoint(endJsonObj.getStr("longitude") + "," + endJsonObj.getStr("latitude"));

            //设置时间
            trace.setTime(endJsonObj.getLong("loc_time") - startJsonObj.getLong("loc_time"));

            return trace;
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }
}
