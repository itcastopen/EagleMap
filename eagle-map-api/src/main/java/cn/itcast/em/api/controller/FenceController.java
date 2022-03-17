package cn.itcast.em.api.controller;

import cn.itcast.em.api.vo.FenceParam;
import cn.itcast.em.enums.FenceType;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.pojo.TraceFence;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.FenceService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 电子围栏相关页面
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/8
 */
@Api(tags = "电子围栏服务")
@RequestMapping("/api/fence")
@RestController
public class FenceController extends BaseController<FenceService> {

    /**
     * 创建圆形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "创建圆形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PostMapping("circle")
    public R<Long> createCircleFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.createFence(fenceParam.getServerId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.CIRCLE, fenceParam.getParam());
    }

    /**
     * 创建多边形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "创建多边形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PostMapping("polygon")
    public R<Long> createPolygonFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.createFence(fenceParam.getServerId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.POLYGON, fenceParam.getParam());
    }

    /**
     * 创建线形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "创建线形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PostMapping("polyline")
    public R<Long> createPolylineFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.createFence(fenceParam.getServerId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.POLYLINE, fenceParam.getParam());
    }

    /**
     * 创建行政区划围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "创建行政区划围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PostMapping("district")
    public R<Long> createDistrictFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.createFence(fenceParam.getServerId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.DISTRICT, fenceParam.getParam());
    }

    /**
     * 更新圆形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "更新圆形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PutMapping("circle")
    public R<String> updateCircleFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.updateFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.CIRCLE, fenceParam.getParam());
    }

    /**
     * 更新多边形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "更新多边形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PutMapping("polygon")
    public R<String> updatePolygonFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.updateFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.POLYGON, fenceParam.getParam());
    }

    /**
     * 更新线形围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "更新线形围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PutMapping("polyline")
    public R<String> updatePolylineFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.updateFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.POLYLINE, fenceParam.getParam());
    }

    /**
     * 更新行政区划围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "更新行政区划围栏", notes = "具体参数参考官方文档：<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/geofence<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track_fence")
    @PutMapping("district")
    public R<String> updateDistrictFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.updateFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getName(),
                fenceParam.getDesc(), FenceType.DISTRICT, fenceParam.getParam());
    }


    /**
     * 删除围栏
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "删除围栏", notes = "必须参数：serverId、fenceIds")
    @DeleteMapping
    public R<String> deleteFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.deleteFence(fenceParam.getServerId(), fenceParam.getFenceIds());
    }

    /**
     * 绑定终端到电子围栏中
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "绑定终端到围栏中", notes = "必须参数：serverId、fenceId、terminalIds")
    @PostMapping("bind")
    public R<String> bindTerminalFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.bindTerminalFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getTerminalIds());
    }

    /**
     * 解绑电子围栏中的终端
     *
     * @param fenceParam
     * @return
     */
    @ApiOperation(value = "解绑围栏中的终端", notes = "必须参数：serverId、fenceId、terminalIds")
    @PostMapping("unbind")
    public R<String> unbindTerminalFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.unbindTerminalFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getTerminalIds());
    }

    /**
     * 分页查询围栏列表
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "page", value = "页数，默认：1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小，默认：20")})
    @ApiOperation(value = "查询围栏列表", notes = "查询围栏列表")
    @GetMapping("list")
    public R<PageResult<TraceFence>> queryFenceList(@RequestParam(value = "provider", defaultValue = "NONE") ServerType provider,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        FenceService fenceService = super.chooseService(provider);
        return fenceService.queryFenceList(page, pageSize);
    }

    /**
     * 分页查询围栏中的终端列表
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id", required = true),
            @ApiImplicitParam(name = "fenceId", value = "围栏id", required = true),
            @ApiImplicitParam(name = "page", value = "页数，默认：1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小，默认：20")})
    @ApiOperation(value = "查询围栏中的终端列表", notes = "必须参数：serverId、fenceId")
    @GetMapping("terminal")
    public R<PageResult<TraceTerminal>> queryTerminalFenceList(@RequestParam(value = "provider", defaultValue = "NONE") ServerType provider,
                                                               @RequestParam(value = "serverId") Long serverId,
                                                               @RequestParam(value = "fenceId") Long fenceId,
                                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        FenceService fenceService = super.chooseService(provider);
        return fenceService.queryTerminalFenceList(serverId, fenceId, page, pageSize);
    }

    /**
     * 根据围栏id查询围栏信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id", required = true),
            @ApiImplicitParam(name = "fenceId", value = "围栏id", required = true)})
    @ApiOperation(value = "查询围栏信息", notes = "必须参数：serverId、fenceId")
    @GetMapping
    public R<TraceFence> queryByFenceId(@RequestParam(value = "provider", defaultValue = "NONE") ServerType provider,
                                        @RequestParam(value = "serverId") Long serverId,
                                        @RequestParam(value = "fenceId") Long fenceId) {
        FenceService fenceService = super.chooseService(provider);
        return fenceService.queryByFenceId(serverId, fenceId);
    }

    /**
     * 查询终端在围栏中的状态，是否超出围栏
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id", required = true),
            @ApiImplicitParam(name = "fenceId", value = "围栏id", required = true),
            @ApiImplicitParam(name = "terminalId", value = "终端id", required = true)})
    @ApiOperation(value = "查询终端在围栏中的状态", notes = "查询终端在围栏中的状态，是否超出围栏，必须参数：serverId、fenceId、TerminalId")
    @GetMapping("status")
    public R<Boolean> queryTerminalStatus(@RequestParam(value = "provider", defaultValue = "NONE") ServerType provider,
                                          @RequestParam(value = "serverId") Long serverId,
                                          @RequestParam(value = "fenceId") Long fenceId,
                                          @RequestParam(value = "terminalId") Long terminalId) {
        FenceService fenceService = super.chooseService(provider);
        return fenceService.queryTerminalStatus(serverId, fenceId, terminalId);
    }

}
