package cn.itcast.em.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.itcast.em.api.vo.TraceParam;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.exception.EagleMapException;
import cn.itcast.em.pojo.Trace;
import cn.itcast.em.service.TraceService;
import cn.itcast.em.service.impl.TraceImageService;
import cn.itcast.em.vo.PageResult;
import cn.itcast.em.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * 轨迹相关业务
 */
@RequestMapping("/api/trace")
@RestController
@Slf4j
public class TraceController extends BaseController<TraceService> {

    @Resource
    private TraceImageService traceImageService;

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
    @PostMapping("info")
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
     * 查询轨迹缩略图
     *
     * @param traceParam
     * @return
     */
    @PostMapping("image")
    public void queryTraceImage(@RequestBody TraceParam traceParam, HttpServletResponse response) {
        TraceService traceService = super.chooseService(traceParam.getProvider());
        Trace result = traceService.queryTraceInfo(traceParam.getServerId(), traceParam.getTerminalId(),
                traceParam.getTraceId(), traceParam.getParam());
        if (null == result) {
            String msg = "Query failed. The track information cannot be found.";
            throw new EagleMapException(msg);
        }

        String pointList = result.getPointList();
        String points;
        switch (traceParam.getProvider()) {
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
            BufferedImage bufferedImage = this.traceImageService.drawImage(points, traceParam.getWidth(), traceParam.getHeight());
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("生成图片出错！{}", traceParam, e);
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
