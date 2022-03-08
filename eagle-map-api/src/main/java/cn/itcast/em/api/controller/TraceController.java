package cn.itcast.em.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.TraceParam;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.pojo.Trace;
import cn.itcast.em.service.TraceService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

/**
 * 轨迹相关业务
 */
@RequestMapping("/api/trace")
@RestController
public class TraceController extends BaseController<TraceService> {

    /**
     * 创建轨迹
     *
     * @param traceParam
     * @return
     */
    @PostMapping
    public R<String> create(@RequestBody TraceParam traceParam) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        String result = traceService.create(traceParam.getServerId(), traceParam.getTerminalId(),
                traceParam.getName());
        if (StrUtil.isNumeric(result)) {
            return R.success(result);
        }
        return R.error(result);
    }

    /**
     * 删除轨迹
     *
     * @param traceParam
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestBody TraceParam traceParam) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        String result = traceService.delete(traceParam.getServerId(), traceParam.getTerminalId(),
                traceParam.getTraceId());
        if (StrUtil.equals("ok", result)) {
            return R.success();
        }
        return R.error(result);
    }

    /**
     * 上传轨迹点
     *
     * @param traceParam
     * @return
     */
    @PostMapping("upload")
    public R<String> upload(@RequestBody TraceParam traceParam) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        String result = traceService.upload(traceParam.getServerId(), traceParam.getTerminalId(),
                traceParam.getTraceId(), traceParam.getPointList());
        if (StrUtil.equals("ok", result)) {
            return R.success();
        }
        return R.error(result);
    }

    /**
     * 查询轨迹详情
     *
     * @param traceParam
     * @return
     */
    @GetMapping("info")
    public R<Trace> queryTraceInfo(@RequestBody TraceParam traceParam) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        Trace result;
        try {
            result = traceService.queryTraceInfo(traceParam.getServerId(), traceParam.getTerminalId(),
                    traceParam.getTraceId(), traceParam.getParam());
            if (null == result) {
                return R.error("Query failed. The track information cannot be found.");
            }
            return R.success(result);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 停止运动，该方法会将轨迹中的轨迹点数据持久化本地数据库中
     *
     * @param traceParam
     * @return
     */
    @PostMapping("stop")
    public R<String> stopTrace(@RequestBody TraceParam traceParam) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        String result;
        try {
            result = traceService.stopTrace(traceParam.getServerId(), traceParam.getTerminalId(),
                    traceParam.getTraceId(), traceParam.getParam());
            if (null == result) {
                return R.error("Failed to stop track.");
            }
            return R.success();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 分页查询轨迹列表，按照轨迹创建时间倒序排序
     *
     * @param page
     * @param pageSize
     * @param provider
     * @return
     */
    @GetMapping("list")
    public R<PageResult<Trace>> queryTracePageList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                   @RequestParam(value = "provider", defaultValue = "NONE") String provider) {
        TraceService traceService = super.chooseService(ServerType.valueOf(provider));
        return R.success(traceService.queryTracePageList(page, pageSize));
    }


}
