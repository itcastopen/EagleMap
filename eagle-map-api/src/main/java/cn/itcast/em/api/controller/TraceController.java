package cn.itcast.em.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.api.vo.TraceParam;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.exception.EagleMapException;
import cn.itcast.em.pojo.Trace;
import cn.itcast.em.service.TraceService;
import cn.itcast.em.service.impl.EagleMapServiceFactory;
import cn.itcast.em.service.impl.TraceImageService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 轨迹相关业务
 */
@Api(tags = "地图轨迹管理")
@RequestMapping("/api/trace")
@RestController
@Slf4j
public class TraceController extends BaseController {

    @Resource
    private TraceImageService traceImageService;

    /**
     * 创建轨迹
     *
     * @param traceParam
     * @return
     */
    @ApiOperation(value = "创建轨迹", notes = "创建轨迹，百度地图是没有轨迹概念的，EagleMap对此做了兼容，自己维护轨迹数据与高德地图保持一致的操作。<br/>高德地图文档：https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t2")
    @PostMapping
    public R<String> create(@RequestBody TraceParam traceParam) {
        TraceService traceService = EagleMapServiceFactory.getService(traceParam.getProvider(), TraceService.class);
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
    @ApiOperation(value = "删除轨迹", notes = "删除轨迹，必须的参数有：serverId、terminalId、traceId")
    @DeleteMapping
    public R<String> delete(@RequestBody TraceParam traceParam) {
        TraceService traceService = EagleMapServiceFactory.getService(traceParam.getProvider(), TraceService.class);
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
    @ApiOperation(value = "上报轨迹点", notes = "可以上报多个轨迹点，必须的参数有：serverId、terminalId、traceId、pointList。<br/>百度地图：https://lbsyun.baidu.com/index.php?title=yingyan/api/v3/trackupload#service-page-anchor7<br/>高德地图：https://lbs.amap.com/api/track/lieying-kaifa/api/track-sdk#t4")
    @PostMapping("upload")
    public R<String> upload(@RequestBody TraceParam traceParam) {
        TraceService traceService = EagleMapServiceFactory.getService(traceParam.getProvider(), TraceService.class);
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
    @ApiOperation(value = "查询轨迹详情", notes = "查询轨迹详情，必须的参数有：serverId、terminalId、traceId，可以通过param参数指定其他参数，具体可参考官方文档。")
    @PostMapping("info")
    public R<Trace> queryTraceInfo(@RequestBody TraceParam traceParam) {
        TraceService traceService = EagleMapServiceFactory.getService(traceParam.getProvider(), TraceService.class);
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
     * 查询轨迹缩略图
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "服务提供商，必须大写，如：BAIDU,AMAP,NONE，默认：高德地图", required = true),
            @ApiImplicitParam(name = "serverId", value = "服务id", required = true),
            @ApiImplicitParam(name = "terminalId", value = "终端id", required = true),
            @ApiImplicitParam(name = "traceId", value = "轨迹id", required = true),
            @ApiImplicitParam(name = "param", value = "参数，json格式"),
            @ApiImplicitParam(name = "width", value = "图片宽度，默认：300"),
            @ApiImplicitParam(name = "height", value = "图片高度，默认：300")})
    @ApiOperation(value = "查询轨迹缩略图", notes = "轨迹缩略图首先查询轨迹详情，再根据详情中的轨迹点生成缩略图，优先查询本地数据库，如本地库中没有数据，再查询地图服务商，可以通过param参数中的local参数进行控制，默认为true，如果不希望查询本地库，就将其设置为false")
    @GetMapping("image")
    public void queryTraceImage(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                @RequestParam(value = "serverId") Long serverId,
                                @RequestParam(value = "terminalId") Long terminalId,
                                @RequestParam(value = "traceId") Long traceId,
                                @RequestParam(value = "param", required = false) String param,
                                @RequestParam(value = "width", required = false) Integer width,
                                @RequestParam(value = "height", required = false) Integer height,
                                HttpServletResponse response) {
        TraceService traceService = EagleMapServiceFactory.getService(provider, TraceService.class);
        Trace result = traceService.queryTraceInfo(serverId, terminalId, traceId, JSONUtil.toBean(param, Map.class));
        if (null == result) {
            String msg = "Query failed. The track information cannot be found.";
            throw new EagleMapException(msg);
        }

        String pointList = result.getPointList();
        String points;
        switch (provider) {
            case AMAP: {
                JSONArray array = JSONUtil.parseArray(pointList);
                points = array.stream()
                        .map(o -> ((JSONObject) o).getStr("location"))
                        .collect(Collectors.joining("|"));
                break;

            }
            case BAIDU: {
                JSONArray array = JSONUtil.parseArray(pointList);
                points = array.stream()
                        .map(o -> {
                            JSONObject obj = (JSONObject) o;
                            return obj.getStr("longitude") + "," + obj.getStr("latitude");
                        }).collect(Collectors.joining("|"));
                break;
            }
            default: {
                return;
            }
        }

        try {
            //绘制图
            BufferedImage bufferedImage = this.traceImageService.drawImage(points, width, height);
            response.addHeader("status", "1");
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("生成图片出错！serverId = {}, terminalId = {}, traceId = {}, param = {}", serverId, terminalId, traceId, param, e);
        }
    }

    /**
     * 停止运动，该方法会将轨迹中的轨迹点数据持久化本地数据库中
     *
     * @param traceParam
     * @return
     */
    @ApiOperation(value = "停止运动", notes = "该操作会将轨迹中的轨迹点数据持久化本地数据库中")
    @PostMapping("stop")
    public R<String> stopTrace(@RequestBody TraceParam traceParam) {
        TraceService traceService = EagleMapServiceFactory.getService(traceParam.getProvider(), TraceService.class);
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
    @ApiOperation(value = "查询轨迹列表", notes = "分页查询轨迹列表，按照轨迹创建时间倒序排序。")
    @GetMapping("list")
    public R<PageResult<Trace>> queryTracePageList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                   @RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider) {
        TraceService traceService = EagleMapServiceFactory.getService(provider, TraceService.class);
        return R.success(traceService.queryTracePageList(page, pageSize));
    }

    /**
     * 根据轨迹id 或 轨迹名称搜索轨迹
     */
    @ApiOperation(value = "查询轨迹列表", notes = "分页查询轨迹列表，按照轨迹创建时间倒序排序。")
    @GetMapping("search")
    public R<List<Trace>> searchTraceList(@RequestParam(value = "provider", defaultValue = "NONE") ProviderType provider,
                                          @RequestParam(value = "traceId", required = false) Long traceId,
                                          @RequestParam(value = "traceName", required = false) String traceName) {
        TraceService traceService = EagleMapServiceFactory.getService(provider, TraceService.class);
        return R.success(traceService.searchTraceList(traceId, traceName));
    }


}
