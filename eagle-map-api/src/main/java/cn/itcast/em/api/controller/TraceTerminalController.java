package cn.itcast.em.api.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.TraceTerminalParam;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.service.TraceTerminalService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/trace/terminal")
@RestController
public class TraceTerminalController extends BaseController<TraceTerminalService> {

    /**
     * 创建终端
     *
     * @param traceTerminalParam
     * @return
     */
    @PostMapping
    public R<String> create(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = super.chooseService(traceTerminalParam.getProvider());
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
    @DeleteMapping
    public R<String> delete(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = super.chooseService(traceTerminalParam.getProvider());
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
    @PutMapping
    public R<String> update(@RequestBody TraceTerminalParam traceTerminalParam) {
        TraceTerminalService traceTerminalService = super.chooseService(traceTerminalParam.getProvider());
        String result = traceTerminalService.update(BeanUtil.toBeanIgnoreError(traceTerminalParam, TraceTerminal.class));
        if (StrUtil.contains(result, "err")) {
            return R.error(result);
        }
        return R.success();
    }

    /**
     * 查询列表
     *
     * @param traceTerminalParam
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public R<PageResult<TraceTerminal>> queryList(@RequestBody TraceTerminalParam traceTerminalParam,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "pageSize", defaultValue = "50") Integer pageSize) {
        TraceTerminalService traceTerminalService = super.chooseService(traceTerminalParam.getProvider());
        PageResult<TraceTerminal> pageResult = traceTerminalService.queryList(traceTerminalParam.getServerId(),
                traceTerminalParam.getTerminalId(), traceTerminalParam.getName(), page, pageSize);
        return R.success(pageResult);
    }
}
