package cn.itcast.em.api.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.itcast.em.api.vo.TraceServerParam;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.service.TraceServerService;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/trace/server")
@RestController
public class TraceServerController extends BaseController<TraceServerService> {

    /**
     * 创建轨迹服务
     *
     * @param traceServerParam
     * @return 成功返回服务id
     */
    @PostMapping
    public R<String> create(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = super.chooseService(traceServerParam.getProvider());
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
    @DeleteMapping
    public R<String> delete(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = super.chooseService(traceServerParam.getProvider());
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
    @PutMapping
    public R<String> update(@RequestBody TraceServerParam traceServerParam) {
        TraceServerService traceServerService = super.chooseService(traceServerParam.getProvider());
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
    @GetMapping("{serverId}")
    public R<TraceServer> queryById(@PathVariable("serverId") Long serverId,
                                    @RequestParam(value = "provider", defaultValue = "NONE") String provider) {
        TraceServerService traceServerService = super.chooseService(ServerType.valueOf(provider));
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
    @GetMapping
    public R<List<TraceServer>> queryAll(@RequestParam(value = "provider", defaultValue = "NONE") String provider) {
        TraceServerService traceServerService = super.chooseService(ServerType.valueOf(provider));
        List<TraceServer> traceServerList = traceServerService.queryAll();
        if (CollUtil.isEmpty(traceServerList)) {
            return R.error("Failed to query traceServer. ");
        }
        return R.success(traceServerList);
    }

}
