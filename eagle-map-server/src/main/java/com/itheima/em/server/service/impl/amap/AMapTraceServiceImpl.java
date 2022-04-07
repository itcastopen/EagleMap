package com.itheima.em.server.service.impl.amap;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.mapper.TraceMapper;
import com.itheima.em.pojo.Trace;
import com.itheima.em.server.config.MybatisPlusConfig;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.TraceService;
import com.itheima.em.vo.PageResult;
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

/**
 * 高德地图的终端管理实现
 */
@Service("AMapTraceService")
@ConditionalOnBean({AMapServerConfig.class, MybatisPlusConfig.class})
public class AMapTraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements TraceService {

    @Resource
    private EagleConfig eagleConfig;
    @Resource
    private AMapWebApiService aMapWebApiService;

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
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/trace/add";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        requestParam.put("trname", name);

        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //将服务数据存储到数据库
            Trace trace = new Trace();
            trace.setProvider(ProviderType.AMAP);
            trace.setServerId(serverId);
            trace.setName(name);
            trace.setTerminalId(terminalId);
            trace.setStatus(0);
            trace.setTraceId(json.getJSONObject("data").getLong("trid"));
            trace.setStartTime(new Date());

            super.save(trace);
            return Convert.toStr(trace.getTraceId());
        });
    }

    @Override
    public String delete(Long serverId, Long terminalId, Long traceId) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/trace/delete";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        requestParam.put("trid", traceId);

        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //删除数据库中的轨迹
            LambdaQueryWrapper<Trace> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Trace::getServerId, serverId);
            lambdaQueryWrapper.eq(Trace::getTerminalId, terminalId);
            lambdaQueryWrapper.eq(Trace::getTraceId, traceId);
            lambdaQueryWrapper.eq(Trace::getProvider, ProviderType.AMAP);
            super.remove(lambdaQueryWrapper);

            return "ok";
        });
    }

    @Override
    public String upload(Long serverId, Long terminalId, Long traceId, List<Map<String, Object>> pointList) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/point/upload";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        requestParam.put("trid", traceId);
        requestParam.put("points", JSONUtil.toJsonStr(pointList));

        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
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
        LambdaQueryWrapper<Trace> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Trace::getServerId, serverId);
        lambdaQueryWrapper.eq(Trace::getTerminalId, terminalId);
        lambdaQueryWrapper.eq(Trace::getTraceId, traceId);
        lambdaQueryWrapper.eq(Trace::getProvider, ProviderType.AMAP);

        Trace trace = super.getOne(lambdaQueryWrapper);
        if (null == trace) {
            return null;
        }

        //查询轨迹详情数据
        this.queryTraceFromAMap(trace, param);

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
        queryWrapper.eq(Trace::getProvider, ProviderType.AMAP);

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

        queryWrapper.eq(Trace::getProvider, ProviderType.AMAP);

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
        LambdaQueryWrapper<Trace> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Trace::getServerId, serverId);
        lambdaQueryWrapper.eq(Trace::getTerminalId, terminalId);
        lambdaQueryWrapper.eq(Trace::getTraceId, traceId);
        lambdaQueryWrapper.eq(Trace::getProvider, ProviderType.AMAP);
        Trace trace = super.getOne(lambdaQueryWrapper);
        if (null == trace) {
            return null;
        }

        if (ObjectUtil.equal(trace.getStatus(), 1) &&
                (CollUtil.isEmpty(param) || Convert.toBool(param.get("local"), true))) {
            //轨迹已结束，并且没有设置param 或 设置的 local为true，直接返回数据
            return trace;
        }

        //从高德地图中查询数据
        this.queryTraceFromAMap(trace, param);
        return trace;
    }

    private void queryTraceFromAMap(Trace trace, Map<String, Object> param) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/trsearch";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }
        int pageSize = 999;
        int page = 1;
        requestParam.put("sid", trace.getServerId());
        requestParam.put("tid", trace.getTerminalId());
        requestParam.put("trid", trace.getTraceId());
        requestParam.put("page", page);
        requestParam.put("pagesize", pageSize);
        Trace traceInfo = executeQuery(url, requestParam);
        if (null == traceInfo) {
            //没有查询到数据
            return;
        }
        if (traceInfo.getSize() > pageSize) {
            //存在下一页数据，循环分页获取数据
            JSONArray jsonArray = JSONUtil.parseArray(traceInfo.getPointList());
            for (; ; ) {
                page++;
                requestParam.put("page", page);
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

        //设置起始位置坐标数据
        JSONArray jsonArray = JSONUtil.parseArray(traceInfo.getPointList());
        if (CollUtil.isNotEmpty(jsonArray)) {
            traceInfo.setStartPoint(((JSONObject) jsonArray.get(0)).getStr("location"));
            traceInfo.setEndPoint(((JSONObject) jsonArray.get(jsonArray.size() - 1)).getStr("location"));
        }

        //拷贝数据到trace对象中
        BeanUtil.copyProperties(traceInfo, trace, CopyOptions.create().ignoreNullValue());
    }

    private Trace executeQuery(String url, Map<String, Object> requestParam) {
        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                throw new RuntimeException(body);
            }
            JSONObject data = json.getJSONObject("data");
            if (data == null) {
                return null;
            }
            JSONArray jsonArray = data.getJSONArray("tracks");
            if (CollUtil.isEmpty(jsonArray)) {
                throw new RuntimeException(body);
            }

            JSONObject jsonData = (JSONObject) jsonArray.get(0);
            Trace trace = new Trace();
            trace.setTime(jsonData.getLong("time"));
            trace.setSize(jsonData.getInt("counts"));
            trace.setDistance(jsonData.getDouble("distance"));
            trace.setPointList(jsonData.getJSONArray("points").toString());

            return trace;
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }
}
