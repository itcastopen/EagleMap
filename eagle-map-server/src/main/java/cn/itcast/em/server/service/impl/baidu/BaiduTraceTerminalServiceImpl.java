package cn.itcast.em.server.service.impl.baidu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.BaiduServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.CoordinateType;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.mapper.TraceTerminalMapper;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.TraceServerService;
import cn.itcast.em.service.TraceTerminalService;
import cn.itcast.em.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图的终端管理实现
 */
@Service("BaiduTraceTerminalService")
@ConditionalOnBean(BaiduServerConfig.class)
public class BaiduTraceTerminalServiceImpl extends ServiceImpl<TraceTerminalMapper, TraceTerminal> implements TraceTerminalService {

    @Resource
    private EagleConfig eagleConfig;

    @Resource
    private BaiduWebApiService baiduWebApiService;

    @Resource(name = "BaiduTraceServerService")
    private TraceServerService traceServerService;

    /**
     * 创建终端，百度地图文档：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity#service-page-anchor5
     *
     * @param serverId 服务id
     * @param name     名称
     * @param desc     描述
     * @param props    自定义字段
     * @return
     */
    @Override
    public String create(Long serverId, String name, String desc, Map<String, Object> props) {
        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/entity/add";

        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("entity_name", name);
        requestParam.put("entity_desc", desc);
        if (CollUtil.isNotEmpty(props)) {
            for (Map.Entry<String, Object> entry : requestParam.entrySet()) {
                requestParam.put(entry.getKey(), entry.getValue());
            }
        }

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("status") != 0) {
                //TODO 如果创建终端失败，需要将服务标记为不可用，目前不太能确定单个服务创建满100万个终端的异常是什么信息
                return body;
            }

            //将终端数据存储到数据库，由于百度地图没有提供终端id，所以需要自己生成终端id
            long id = IdUtil.getSnowflake().nextId();
            TraceTerminal traceTerminal = new TraceTerminal();
            traceTerminal.setId(id);
            traceTerminal.setProvider(ProviderType.BAIDU);
            traceTerminal.setName(name);
            traceTerminal.setDesc(desc);
            traceTerminal.setServerId(serverId);
            traceTerminal.setTerminalId(id);
            traceTerminal.setProps(JSONUtil.toJsonStr(props));

            super.save(traceTerminal);
            return Convert.toStr(traceTerminal.getTerminalId());
        });
    }

    /**
     * 不指定服务，由程序自动选择服务，如果没有可用的服务会自动创建服务
     *
     * @param name  名称
     * @param desc  描述
     * @param props 自定义字段
     * @return
     */
    @Override
    public String create(String name, String desc, Map<String, Object> props) {
        //查询可用的服务
        TraceServer traceServer = this.traceServerService.queryAvailableServer();
        if (null == traceServer) {
            return "err: No services available.";
        }
        return this.create(traceServer.getServerId(), name, desc, props);
    }

    @Override
    public String delete(Long serverId, Long terminalId) {
        //查询终端数据
        TraceTerminal traceTerminal = super.getById(terminalId);
        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/entity/delete";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("entity_name", traceTerminal.getName());
        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return body;
            }

            //删除数据库中的数据
            return super.removeById(terminalId) ? null : "err";
        });
    }

    @Override
    public String update(TraceTerminal traceTerminal) {
        //查询终端数据
        TraceTerminal traceTerminalData = super.getById(traceTerminal.getTerminalId());

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/entity/update";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", traceTerminal.getServerId());
        requestParam.put("entity_name", traceTerminal.getName());
        requestParam.put("entity_desc", traceTerminal.getDesc());
        if (StrUtil.isNotEmpty(traceTerminal.getProps())) {
            JSONObject jsonObject = JSONUtil.parseObj(traceTerminal.getProps());
            for (String key : jsonObject.keySet()) {
                requestParam.put(key, jsonObject.get(key));
            }
        }

        return this.baiduWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return body;
            }

            //更新数据库中的数据
            traceTerminalData.setDesc(traceTerminal.getDesc());
            traceTerminalData.setProps(traceTerminal.getProps());

            return super.updateById(traceTerminalData) ? null : "err";
        });
    }

    @Override
    public PageResult<TraceTerminal> queryList(Long serverId, Long terminalId, String name, Integer page, Integer pageSize) {
        PageResult<TraceTerminal> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setPageSize(pageSize);
        pageResult.setItems(new ArrayList<>());
        if (null != terminalId) {
            //根据主键id查询
            pageResult.getItems().add(super.getById(terminalId));
            return pageResult;
        }

        if (StrUtil.isNotEmpty(name)) {
            //根据名称查询
            LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceTerminal::getName, name);
            queryWrapper.eq(TraceTerminal::getProvider, ProviderType.BAIDU);
            queryWrapper.eq(TraceTerminal::getServerId, serverId);
            pageResult.getItems().add(super.getOne(queryWrapper));
            return pageResult;
        }

        //数据库查询列表
        LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceTerminal::getProvider, ProviderType.BAIDU);
        queryWrapper.eq(TraceTerminal::getServerId, serverId);
        queryWrapper.orderByDesc(TraceTerminal::getCreated);

        Page<TraceTerminal> traceTerminalPage = new Page<>(page, pageSize);

        Page<TraceTerminal> terminalPage = super.page(traceTerminalPage, queryWrapper);
        return pageResult.convert(terminalPage);
    }

    @Override
    public String queryLastPoint(Long serverId, Long terminalId, Long traceId) {
        //查询终端数据
        TraceTerminal traceTerminalData = super.getById(terminalId);

        String url = this.eagleConfig.getBaiduYingYanApi() + "/api/v3/entity/list";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("service_id", serverId);
        requestParam.put("filter", "entity_names:" + traceTerminalData.getName());
        requestParam.put("coord_type_output", CoordinateType.EAGLE.getValue());

        return this.baiduWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("status") != 0) {
                return body;
            }
            return json.getJSONArray("entities").toString();
        });
    }

    @Override
    public int getOrder() {
        return EagleOrdered.TWO;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.BAIDU;
    }

}
