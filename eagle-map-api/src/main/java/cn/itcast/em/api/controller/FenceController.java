package cn.itcast.em.api.controller;

import cn.itcast.em.api.vo.FenceParam;
import cn.itcast.em.enums.FenceType;
import cn.itcast.em.pojo.TraceFence;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.FenceService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

/**
 * 电子围栏相关页面
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/8
 */
@RequestMapping("/api/fence")
@RestController
public class FenceController extends BaseController<FenceService> {

    /**
     * 创建圆形围栏
     *
     * @param fenceParam
     * @return
     */
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
    @PostMapping("unbind")
    public R<String> unbindTerminalFence(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.unbindTerminalFence(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getTerminalIds());
    }

    /**
     * 分页查询围栏列表
     */
    @GetMapping("list")
    public R<PageResult<TraceFence>> queryFenceList(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.queryFenceList(fenceParam.getPage(), fenceParam.getPageSize());
    }

    /**
     * 分页查询围栏中的终端列表
     */
    @GetMapping("terminal")
    public R<PageResult<TraceTerminal>> queryTerminalFenceList(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.queryTerminalFenceList(fenceParam.getServerId(), fenceParam.getFenceId(), fenceParam.getPage(), fenceParam.getPageSize());
    }

    /**
     * 根据围栏id查询围栏信息
     */
    @GetMapping
    public R<TraceFence> queryByFenceId(@RequestBody FenceParam fenceParam) {
        FenceService fenceService = super.chooseService(fenceParam.getProvider());
        return fenceService.queryByFenceId(fenceParam.getServerId(), fenceParam.getFenceId());
    }

}
