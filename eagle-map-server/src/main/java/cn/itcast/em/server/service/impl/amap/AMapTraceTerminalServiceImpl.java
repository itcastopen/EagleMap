package cn.itcast.em.server.service.impl.amap;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.config.AMapServerConfig;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.mapper.TraceTerminalMapper;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.EagleOrdered;
import cn.itcast.em.service.TraceServerService;
import cn.itcast.em.service.TraceTerminalService;
import cn.itcast.em.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 高德地图的终端管理实现
 */
@Service("AMapTraceTerminalService")
@ConditionalOnBean(AMapServerConfig.class)
@ConditionalOnProperty(name = "eagle.service-mode", havingValue = "COMPLETE")
public class AMapTraceTerminalServiceImpl extends ServiceImpl<TraceTerminalMapper, TraceTerminal> implements TraceTerminalService {

    @Resource
    private EagleConfig eagleConfig;

    @Resource
    private AMapWebApiService aMapWebApiService;

    @Resource(name = "AMapTraceServerService")
    private TraceServerService traceServerService;

    /**
     * 创建终端，高德文档：https://lbs.amap.com/api/track/lieying-kaifa/api/terminal#t2
     *
     * @param serverId 服务id
     * @param name     名称
     * @param desc     描述
     * @param props    自定义字段
     * @return
     */
    @Override
    public String create(Long serverId, String name, String desc, Map<String, Object> props) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/add";
        String propsJson = JSONUtil.toJsonStr(props);
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("name", name);
        requestParam.put("desc", desc);
        requestParam.put("props", propsJson);

        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //将服务数据存储到数据库
            TraceTerminal traceTerminal = new TraceTerminal();
            traceTerminal.setProvider(ProviderType.AMAP);
            traceTerminal.setName(name);
            traceTerminal.setDesc(desc);
            traceTerminal.setServerId(serverId);
            traceTerminal.setProps(propsJson);
            traceTerminal.setTerminalId(json.getJSONObject("data").getLong("tid"));

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
        Long serverId;
        if (null != traceServer) {
            serverId = traceServer.getServerId();
        } else {
            //创建新的服务
            String serverName = "MY-SERVER-" + DateUtil.format(new Date(), "yyyyMMdd");
            String serverDesc = "Service automatically created on " + DateUtil.now();
            String result = this.traceServerService.create(serverName, serverDesc);
            if (StrUtil.contains(result, "err")) {
                //服务创建失败
                return "err: Failed to create AMAP service.";
            }
            serverId = Convert.toLong(result);
        }
        return this.create(serverId, name, desc, props);
    }

    @Override
    public String delete(Long serverId, Long terminalId) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/delete";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //删除数据库中的数据
            LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceTerminal::getServerId, serverId);
            queryWrapper.eq(TraceTerminal::getTerminalId, terminalId);
            queryWrapper.eq(TraceTerminal::getProvider, ProviderType.AMAP);
            return super.remove(queryWrapper) ? null : "err";
        });
    }

    @Override
    public String update(TraceTerminal traceTerminal) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/update";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", traceTerminal.getServerId());
        requestParam.put("tid", traceTerminal.getTerminalId());
        requestParam.put("name", traceTerminal.getName());
        requestParam.put("desc", traceTerminal.getDesc());
        requestParam.put("props", traceTerminal.getProps());
        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //更新数据库中的数据
            LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceTerminal::getServerId, traceTerminal.getServerId());
            queryWrapper.eq(TraceTerminal::getTerminalId, traceTerminal.getTerminalId());
            queryWrapper.eq(TraceTerminal::getProvider, ProviderType.AMAP);

            return super.update(traceTerminal, queryWrapper) ? null : "err";
        });
    }

    @Override
    public PageResult<TraceTerminal> queryList(Long serverId, Long terminalId, String name, Integer page, Integer pageSize) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/list";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        requestParam.put("name", name);
        requestParam.put("page", page);

        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            PageResult pageResult = new PageResult();
            pageResult.setPage(page);
            pageResult.setPageSize(pageSize);
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return pageResult;
            }

            JSONArray jsonArray = json.getJSONObject("data").getJSONArray("results");
            if (CollUtil.isEmpty(jsonArray)) {
                return pageResult;
            }

            pageResult.setTotal(json.getJSONObject("data").getInt("count"));

            pageResult.setItems(jsonArray.stream().map(o -> {
                JSONObject obj = ((JSONObject) o);
                TraceTerminal traceTerminal = new TraceTerminal();
                traceTerminal.setServerId(serverId);
                traceTerminal.setProvider(ProviderType.AMAP);
                traceTerminal.setTerminalId(obj.getLong("tid"));
                traceTerminal.setName(obj.getStr("name"));
                traceTerminal.setDesc(obj.getStr("desc"));
                traceTerminal.setCreated(new Date(obj.getLong("createtime")));
                traceTerminal.setUpdated(new Date(obj.getLong("locatetime")));
                traceTerminal.setProps(Convert.toStr(obj.getJSONObject("props")));
                return traceTerminal;
            }).collect(Collectors.toList()));

            return pageResult;

        });
    }

    @Override
    public String queryLastPoint(Long serverId, Long terminalId, Long traceId) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/terminal/lastpoint";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("tid", terminalId);
        requestParam.put("trid", traceId);
        return this.aMapWebApiService.doGet(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }
            return Convert.toStr(json.getJSONObject("data"));
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
