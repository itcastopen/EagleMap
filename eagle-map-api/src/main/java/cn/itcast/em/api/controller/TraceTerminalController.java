package cn.itcast.em.api.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.TraceTerminalParam;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.TraceTerminalService;
import cn.itcast.em.service.impl.EagleMapServiceFactory;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "轨迹终端管理")
@RequestMapping("/api/trace/terminal")
@RestController
public class TraceTerminalController extends BaseController {

    /**
     * 创建终端
     *
     * @param traceTerminalParam
     * @return
     */
    @ApiOperation(value = "创建终端", notes = "百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/terminal")
    @PostMapping
    public R<String> create(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = EagleMapServiceFactory.getService(traceTerminalParam.getProvider(), TraceTerminalService.class);
        String result;
        if (traceTerminalParam.getServerId() != null) {
            //指定服务id
            result = traceTerminalService.create(traceTerminalParam.getServerId(),
                    traceTerminalParam.getName(),
                    traceTerminalParam.getDesc(),
                    traceTerminalParam.getProps());
        } else {
            //未指定服务id，自动选择
            result = traceTerminalService.create(traceTerminalParam.getName(),
                    traceTerminalParam.getDesc(),
                    traceTerminalParam.getProps());
        }

        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success(result);
    }

    /**
     * 删除轨迹终端
     *
     * @param traceTerminalParam
     * @return
     */
    @ApiOperation(value = "删除终端", notes = "百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/terminal")
    @DeleteMapping
    public R<String> delete(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = EagleMapServiceFactory.getService(traceTerminalParam.getProvider(), TraceTerminalService.class);
        String result = traceTerminalService.delete(traceTerminalParam.getServerId(), traceTerminalParam.getTerminalId());
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success();
    }

    /**
     * 更新轨迹终端
     *
     * @param traceTerminalParam
     * @return
     */
    @ApiOperation(value = "更新终端", notes = "百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entity<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/terminal")
    @PutMapping
    public R<String> update(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = EagleMapServiceFactory.getService(traceTerminalParam.getProvider(), TraceTerminalService.class);
        String result = traceTerminalService.update(BeanUtil.toBeanIgnoreError(traceTerminalParam, TraceTerminal.class));
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success();
    }

    /**
     * 查询列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id"),
            @ApiImplicitParam(name = "terminalId", value = "终端id，非必须，如果指定就按照终端id查询，否则查询列表"),
            @ApiImplicitParam(name = "name", value = "终端名称，非必须，如果指定就按照终端名称查询，否则查询列表"),
            @ApiImplicitParam(name = "page", value = "页数，默认：1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小，默认：50")})
    @ApiOperation(value = "查询终端列表", notes = "查询终端列表，如果指定了 终端id 或 名称 将查询具体的数据，否则查询列表数据")
    @GetMapping
    public R<PageResult<TraceTerminal>> queryList(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                                  @RequestParam(value = "serverId") Long serverId,
                                                  @RequestParam(value = "terminalId", required = false) Long terminalId,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", defaultValue = "50") Integer pageSize) {
        TraceTerminalService traceTerminalService = EagleMapServiceFactory.getService(provider, TraceTerminalService.class);
        PageResult<TraceTerminal> pageResult = traceTerminalService.queryList(serverId, terminalId, name, page, pageSize);
        return R.success(pageResult);
    }

    /**
     * 查询终端在某个轨迹中的最新位置
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id"),
            @ApiImplicitParam(name = "terminalId", value = "终端id"),
            @ApiImplicitParam(name = "traceId", value = "轨迹id")})
    @ApiOperation(value = "查询终端在某个轨迹中的最新位置", notes = "查询终端在某个轨迹中的最新位置")
    @GetMapping("last/point")
    public R<String> queryLastPoint(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                    @RequestParam(value = "serverId") Long serverId,
                                    @RequestParam(value = "terminalId") Long terminalId,
                                    @RequestParam(value = "traceId", required = false) Long traceId) {
        TraceTerminalService traceTerminalService = EagleMapServiceFactory.getService(provider, TraceTerminalService.class);
        String result = traceTerminalService.queryLastPoint(serverId, terminalId, traceId);
        return R.success(result);
    }
}
