package com.itheima.em.api.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.itheima.em.api.vo.TraceServerParam;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.pojo.TraceServer;
import com.itheima.em.service.TraceServerService;
import com.itheima.em.service.impl.EagleMapServiceFactory;
import com.itheima.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "轨迹服务管理")
@RequestMapping("/api/trace/server")
@RestController
public class TraceServerController extends BaseController {

    /**
     * 创建轨迹服务
     *
     * @param traceServerParam
     * @return 成功返回服务id
     */
    @ApiOperation(value = "创建轨迹服务", notes = "使用轨迹功能必须先创建轨迹服务，百度地图不支持通过接口创建，请通过百度地图的轨迹服务管理系统创建，高德地图支持通过接口创建。<br/>百度地图管理地址：https://lbsyun.baidu.com/trace/admin/service")
    @PostMapping
    public R<String> create(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = EagleMapServiceFactory.getService(traceServerParam.getProvider(), TraceServerService.class);
        String result = traceServerService.create(traceServerParam.getName(), traceServerParam.getDesc());
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success(result);
    }

    /**
     * 删除轨迹服务
     *
     * @param traceServerParam
     * @return
     */
    @ApiOperation(value = "删除轨迹服务", notes = "百度地图不支持通过接口删除，此方法仅仅是从数据库中删除数据，并不会从百度地图中删除，高德地图支持通过接口删除。<br/>百度地图管理地址：https://lbsyun.baidu.com/trace/admin/service")
    @DeleteMapping
    public R<String> delete(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = EagleMapServiceFactory.getService(traceServerParam.getProvider(), TraceServerService.class);
        String result = traceServerService.delete(traceServerParam.getServerId());
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success();
    }

    /**
     * 更新轨迹服务
     *
     * @param traceServerParam
     * @return
     */
    @ApiOperation(value = "更新轨迹服务", notes = "百度地图不支持通过接口更新，此方法仅仅是从数据库中更新数据，并不会从百度地图中更新，高德地图支持通过接口更新。<br/>百度地图管理地址：https://lbsyun.baidu.com/trace/admin/service")
    @PutMapping
    public R<String> update(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = EagleMapServiceFactory.getService(traceServerParam.getProvider(), TraceServerService.class);
        String result = traceServerService.update(traceServerParam.getServerId(), traceServerParam.getName(), traceServerParam.getDesc());
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success(result);
    }

    /**
     * 根据serverId查询轨迹服务
     *
     * @return
     */
    @ApiOperation(value = "查询单个轨迹服务", notes = "通过serverId查询单个轨迹服务信息")
    @GetMapping("{serverId}")
    public R<TraceServer> queryById(@PathVariable("serverId") Long serverId,
                                    @RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider) {
        TraceServerService traceServerService = EagleMapServiceFactory.getService(provider, TraceServerService.class);
        TraceServer traceServer = traceServerService.queryByServerId(serverId);
        if (traceServer == null) {
            return R.error("Failed to query traceServer. ");
        }
        return R.success(traceServer);
    }

    /**
     * 查询所有的轨迹服务
     *
     * @return
     */
    @ApiOperation(value = "查询所有的轨迹服务", notes = "查询所有的轨迹服务。")
    @GetMapping
    public R<List<TraceServer>> queryAll(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider) {
        TraceServerService traceServerService = EagleMapServiceFactory.getService(provider, TraceServerService.class);
        List<TraceServer> traceServerList = traceServerService.queryAll();
        if (CollUtil.isEmpty(traceServerList)) {
            return R.error("Failed to query traceServer. ");
        }
        return R.success(traceServerList);
    }

}
