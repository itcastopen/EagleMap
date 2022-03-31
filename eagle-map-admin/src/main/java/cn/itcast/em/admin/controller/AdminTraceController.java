package cn.itcast.em.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import cn.itcast.em.admin.vo.TraceServerVO;
import cn.itcast.em.admin.vo.TraceTerminalVO;
import cn.itcast.em.api.controller.FenceController;
import cn.itcast.em.api.controller.TraceController;
import cn.itcast.em.api.controller.TraceServerController;
import cn.itcast.em.api.controller.TraceTerminalController;
import cn.itcast.em.api.vo.FenceParam;
import cn.itcast.em.api.vo.TraceParam;
import cn.itcast.em.api.vo.TraceTerminalParam;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.pojo.Trace;
import cn.itcast.em.pojo.TraceFence;
import cn.itcast.em.pojo.TraceServer;
import cn.itcast.em.pojo.TraceTerminal;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 轨迹相关的业务实现
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/18
 */
@RequestMapping("sys")
@RestController
public class AdminTraceController {

    @Resource
    private TraceServerController traceServerController;
    @Resource
    private TraceController traceController;
    @Resource
    private FenceController fenceController;
    @Resource
    private TraceTerminalController traceTerminalController;

    /**
     * 分页查询轨迹服务列表
     *
     * @param page
     * @param pageSize
     * @param provider
     * @return
     */
    @GetMapping("/trace/server")
    public R<PageResult<TraceServerVO>> listTraceServer(@RequestParam("provider") ProviderType provider,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        PageResult<TraceServerVO> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setPageSize(pageSize);

        R<List<TraceServer>> result = this.traceServerController.queryAll(provider);
        List<TraceServer> list = result.getData();

        if (CollUtil.isEmpty(list)) {
            return R.success(pageResult);
        }
        pageResult.setTotal(CollUtil.size(list)); //数据总条数
        pageResult.setPageCount(PageUtil.totalPage(pageResult.getTotal(), pageSize));
        pageResult.setItems(new ArrayList<>());

        //分页查询数据
        int[] startEnd = PageUtil.transToStartEnd(page - 1, pageSize);
        int start = startEnd[0];
        int end = Math.min(startEnd[1], pageResult.getTotal());

        for (int i = start; i < end; i++) {
            pageResult.getItems().add(TraceServerVO.convert(list.get(i)));
        }

        return R.success(pageResult);
    }

    /**
     * 分页查询轨迹列表，按照轨迹创建时间倒序排序
     *
     * @param page
     * @param pageSize
     * @param provider
     * @return
     */
    @GetMapping("trace")
    public R<PageResult<Trace>> queryTracePageList(@RequestParam(value = "provider") ProviderType provider,
                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                   @RequestParam(value = "traceId", required = false) Long traceId,
                                                   @RequestParam(value = "traceName", required = false) String traceName) {
        if (null != traceId || StrUtil.isNotEmpty(traceName)) {
            //搜索
            List<Trace> list = this.traceController.searchTraceList(provider, traceId, traceName).getData();
            PageResult<Trace> pageResult = new PageResult<>();
            pageResult.setPage(page);
            pageResult.setPageSize(pageSize);
            pageResult.setTotal(CollUtil.size(list));
            pageResult.setPageCount(PageUtil.totalPage(pageResult.getTotal(), pageSize));
            pageResult.setItems(list);
            return R.success(pageResult);
        }

        return this.traceController.queryTracePageList(page, pageSize, provider);
    }

    /**
     * 删除轨迹
     *
     * @param provider   服务提供者
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @return
     */
    @DeleteMapping("trace/{traceId}")
    public R<String> deleteTrace(@RequestParam(value = "provider") ProviderType provider,
                                 @RequestParam(value = "serverId") Long serverId,
                                 @RequestParam(value = "terminalId") Long terminalId,
                                 @PathVariable("traceId") Long traceId) {
        TraceParam traceParam = new TraceParam();
        traceParam.setProvider(provider);
        traceParam.setServerId(serverId);
        traceParam.setTerminalId(terminalId);
        traceParam.setTraceId(traceId);
        return this.traceController.delete(traceParam);
    }

    /**
     * 分页查询围栏列表
     *
     * @return
     */
    @GetMapping("fence")
    public R<PageResult<TraceFence>> queryFenceList(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                    @RequestParam(value = "fenceName", required = false) String fenceName,
                                                    @RequestParam(value = "fenceId", required = false) Long fenceId) {
        if (null != fenceId || StrUtil.isNotEmpty(fenceName)) {
            //搜索
            List<TraceFence> list = this.fenceController.searchTraceFenceList(provider, fenceId, fenceName).getData();
            PageResult<TraceFence> pageResult = new PageResult<>();
            pageResult.setPage(page);
            pageResult.setPageSize(pageSize);
            pageResult.setTotal(CollUtil.size(list));
            pageResult.setPageCount(PageUtil.totalPage(pageResult.getTotal(), pageSize));
            pageResult.setItems(list);
            return R.success(pageResult);
        }

        return this.fenceController.queryFenceList(provider, page, pageSize);
    }

    /**
     * 删除围栏
     *
     * @param provider 服务商
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @return 结果
     */
    @DeleteMapping("fence/{fenceId}")
    public R<String> deleteFence(@RequestParam(value = "provider") ProviderType provider,
                                 @RequestParam(value = "serverId") Long serverId,
                                 @PathVariable("fenceId") Long fenceId) {
        FenceParam fenceParam = new FenceParam();
        fenceParam.setFenceIds(new Long[]{fenceId});
        fenceParam.setServerId(serverId);
        fenceParam.setProvider(provider);
        return this.fenceController.deleteFence(fenceParam);
    }

    /**
     * 根据围栏id查询围栏信息
     *
     * @param provider 服务商
     * @param serverId 服务id
     * @param fenceId  围栏id
     * @return
     */
    @GetMapping("fence/{fenceId}")
    public R<TraceFence> queryByFenceId(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                        @RequestParam(value = "serverId") Long serverId,
                                        @PathVariable(value = "fenceId") Long fenceId) {
        return this.fenceController.queryByFenceId(provider, serverId, fenceId);
    }

    /**
     * 删除终端
     *
     * @param provider   服务商
     * @param serverId   服务id
     * @param terminalId 终端id
     * @return
     */
    @DeleteMapping("/terminal/{terminalId}")
    public R<String> deleteTerminal(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                    @RequestParam(value = "serverId") Long serverId,
                                    @PathVariable(value = "terminalId") Long terminalId) {
        TraceTerminalParam traceTerminalParam = new TraceTerminalParam();
        traceTerminalParam.setProvider(provider);
        traceTerminalParam.setServerId(serverId);
        traceTerminalParam.setTerminalId(terminalId);
        return this.traceTerminalController.delete(traceTerminalParam);
    }

    /**
     * 查询终端列表，如果 fenceId 参数为空查询终端列表，否则查询围栏下的终端列表
     * 如果terminalId或terminalName不为空，按照条件筛选
     *
     * @param provider     服务商
     * @param serverId     服务id
     * @param fenceId      围栏id
     * @param terminalId   终端id
     * @param terminalName 终端名称
     * @param page         页数
     * @param pageSize     页面大小
     * @return 终端列表
     */
    @GetMapping("/terminal")
    public R<PageResult<TraceTerminalVO>> queryTerminalList(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                                            @RequestParam(value = "serverId") Long serverId,
                                                            @PathVariable(value = "fenceId", required = false) Long fenceId,
                                                            @PathVariable(value = "terminalId", required = false) Long terminalId,
                                                            @PathVariable(value = "terminalName", required = false) String terminalName,
                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PageResult<TraceTerminal> pageResult;
        if (ObjectUtil.isNotEmpty(fenceId)) {
            //查询围栏下的终端列表
            pageResult = this.fenceController.queryTerminalFenceList(provider, serverId, fenceId, page, pageSize).getData();
        } else {
            //查询终端列表
            pageResult = this.traceTerminalController.queryList(provider, serverId, terminalId, terminalName, page, pageSize).getData();
        }

        PageResult<TraceTerminalVO> voPageResult = new PageResult<>();
        if (CollUtil.isEmpty(pageResult.getItems())) {
            return R.success(voPageResult);
        }

        //转化数据
        BeanUtil.copyProperties(pageResult, voPageResult, "items");

        voPageResult.setItems(pageResult.getItems().stream()
                .map(traceTerminal -> {
                    TraceTerminalVO traceTerminalVO = BeanUtil.toBeanIgnoreError(traceTerminal, TraceTerminalVO.class);
                    this.traceTerminalController.queryLastPoint(provider, serverId, traceTerminal.getTerminalId(), null).getCode();
                    return traceTerminalVO;
                }).collect(Collectors.toList()));
        if (ObjectUtil.isNotEmpty(fenceId)) {
            //查询是否超出围栏
            voPageResult.getItems().forEach(traceTerminalVO -> {
                traceTerminalVO.setIsOut(this.fenceController.queryTerminalStatus(provider, serverId, fenceId, traceTerminalVO.getTerminalId()).getData());
            });
        }

        return R.success(voPageResult);
    }

    /**
     * 查询轨迹缩略图
     *
     * @param provider   服务提供者
     * @param serverId   服务id
     * @param terminalId 终端id
     * @param traceId    轨迹id
     * @param param      其他参数，用于查询轨迹，json格式
     * @param width      图片宽度，默认：300
     * @param height     图片高度，默认：300
     * @param response   响应对象
     */
    @GetMapping("/trace/image")
    public void queryTraceImage(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                @RequestParam(value = "serverId") Long serverId,
                                @RequestParam(value = "terminalId") Long terminalId,
                                @RequestParam(value = "traceId") Long traceId,
                                @RequestParam(value = "param", required = false) String param,
                                @RequestParam(value = "width", required = false) Integer width,
                                @RequestParam(value = "height", required = false) Integer height,
                                HttpServletResponse response) {
        this.traceController.queryTraceImage(provider, serverId, terminalId, traceId, param, width, height, response);
    }
}
