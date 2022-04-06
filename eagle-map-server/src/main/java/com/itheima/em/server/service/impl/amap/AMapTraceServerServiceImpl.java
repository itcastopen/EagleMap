package com.itheima.em.server.service.impl.amap;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.em.config.AMapServerConfig;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.mapper.TraceServerMapper;
import com.itheima.em.pojo.TraceServer;
import com.itheima.em.server.config.MybatisPlusConfig;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.service.TraceServerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高德地图的轨迹服务实现类
 */
@Service("AMapTraceServerService")
@ConditionalOnBean({AMapServerConfig.class, MybatisPlusConfig.class})
public class AMapTraceServerServiceImpl extends ServiceImpl<TraceServerMapper, TraceServer> implements TraceServerService {

    @Resource
    private EagleConfig eagleConfig;

    @Resource
    private AMapWebApiService aMapWebApiService;

    /**
     * 创建服务，高德接口文档：https://lbs.amap.com/api/track/lieying-kaifa/api/service#t2
     *
     * @param name 服务名称
     * @param desc 服务描述，非必须
     * @return
     */
    @Override
    public String create(String name, String desc) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/service/add";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("name", name);
        requestParam.put("desc", desc);
        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(response.body());
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //将服务数据存储到数据库
            TraceServer traceServer = new TraceServer();
            traceServer.setProvider(ProviderType.AMAP);
            traceServer.setName(name);
            traceServer.setDesc(desc);
            traceServer.setStatus(true);
            traceServer.setServerId(json.getJSONObject("data").getLong("sid"));

            super.save(traceServer);

            return Convert.toStr(traceServer.getServerId());
        });
    }

    @Override
    public String delete(Long serverId) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/service/delete";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //删除数据库中的数据
            LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceServer::getServerId, serverId);
            queryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);
            return super.remove(queryWrapper) ? null : "err";
        });
    }

    @Override
    public String update(Long serverId, String name, String desc) {
        String url = this.eagleConfig.getAmapTsApi() + "/v1/track/service/update";
        //封装请求参数
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("sid", serverId);
        requestParam.put("name", name);
        requestParam.put("desc", desc);
        return this.aMapWebApiService.doPost(url, requestParam, response -> {
            String body = response.body();
            JSONObject json = JSONUtil.parseObj(body);
            if (!response.isOk() || json.getInt("errcode") != 10000) {
                return body;
            }

            //更新数据库中的数据
            TraceServer traceServer = new TraceServer();
            traceServer.setName(name);
            traceServer.setDesc(desc);

            LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TraceServer::getServerId, serverId);
            queryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);

            return super.update(traceServer, queryWrapper) ? null : "err";
        });
    }

    @Override
    public List<TraceServer> queryAll() {
        LambdaQueryWrapper<TraceServer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);
        return super.list(lambdaQueryWrapper);
    }

    @Override
    public TraceServer queryByServerId(Long serverId) {
        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getServerId, serverId);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);
        return super.getOne(queryWrapper);
    }

    @Override
    public int getOrder() {
        return EagleOrdered.ONE;
    }

    @Override
    public TraceServer queryAvailableServer() {
        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getStatus, true);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);

        List<TraceServer> serverList = super.list(queryWrapper);
        if (CollUtil.isNotEmpty(serverList)) {
            return serverList.get(0);
        }
        return null;
    }

    @Override
    public Boolean markNotAvailable(Long serverId) {
        LambdaQueryWrapper<TraceServer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TraceServer::getServerId, serverId);
        queryWrapper.eq(TraceServer::getProvider, ProviderType.AMAP);

        TraceServer traceServer = new TraceServer();
        traceServer.setStatus(false);
        return super.update(traceServer, queryWrapper);
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.AMAP;
    }
}
