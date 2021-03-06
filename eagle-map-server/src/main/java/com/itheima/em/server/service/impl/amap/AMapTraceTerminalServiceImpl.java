package com.itheima.em.server.service.impl.amap;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.mapper.TraceTerminalMapper;
import com.itheima.em.pojo.TraceServer;
import com.itheima.em.pojo.TraceTerminal;
import com.itheima.em.server.config.MybatisPlusConfig;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.TraceServerService;
import com.itheima.em.service.TraceTerminalService;
import com.itheima.em.vo.PageResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图的终端管理实现
 */
@Service("AMapTraceTerminalService")
@ConditionalOnBean({AMapServerConfig.class, MybatisPlusConfig.class})
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
            queryWrapper.like(TraceTerminal::getName, name);
            queryWrapper.eq(TraceTerminal::getProvider, ProviderType.AMAP);
            queryWrapper.eq(TraceTerminal::getServerId, serverId);
            pageResult.setItems(super.list(queryWrapper));
            return pageResult;
        }

        //数据库查询列表
        LambdaQueryWrapper<TraceTerminal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceTerminal::getProvider, ProviderType.AMAP);
        queryWrapper.eq(TraceTerminal::getServerId, serverId);
        queryWrapper.orderByDesc(TraceTerminal::getCreated);

        Page<TraceTerminal> traceTerminalPage = new Page<>(page, pageSize);

        Page<TraceTerminal> terminalPage = super.page(traceTerminalPage, queryWrapper);
        return pageResult.convert(terminalPage);
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
