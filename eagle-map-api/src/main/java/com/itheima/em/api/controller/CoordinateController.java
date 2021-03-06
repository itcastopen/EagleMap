package com.itheima.em.api.controller;

import cn.hutool.core.collection.CollUtil;
import com.itheima.em.api.vo.ConvertParam;
import com.itheima.em.api.vo.ConvertToGcj02Param;
import com.itheima.em.enums.CoordinateType;
import com.itheima.em.service.CoordinateService;
import com.itheima.em.service.impl.EagleMapServiceFactory;
import com.itheima.em.vo.CoordinateVo;
import com.itheima.em.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "地图基础服务")
@RestController
@RequestMapping("api/coordinate")
public class CoordinateController extends BaseController {

    /**
     * 将指定的坐标转化为gcj02坐标体系
     *
     * @param param 原始坐标类型与原始坐标
     * @return gcj02坐标值
     */
    @ApiOperation(value = "转化为gcj02坐标体系", notes = "将坐标转化为gcj02，该方法不能指定目标类型。")
    @PostMapping("convert/gcj02")
    public R<List<CoordinateVo>> convertToGcj02(@RequestBody ConvertToGcj02Param param) {
        CoordinateType coordinateType = CoordinateType.valueOf(param.getFromType());
        CoordinateService coordinateService = EagleMapServiceFactory.getService(param.getProvider(), CoordinateService.class);
        List<CoordinateVo> list = coordinateService.convertToGcj02(coordinateType, param.getCoordinates());
        if (CollUtil.isEmpty(list)) {
            return R.error("Convert to gcj02 failed.");
        }
        return R.success(list);
    }

    /**
     * 指定坐标体系转化
     *
     * @param convertParam 坐标转化参数
     * @return 目标坐标
     */
    @ApiOperation(value = "指定坐标体系转化", notes = "可以通过指定坐标类型，将原坐标转化为目标坐标，例如：将百度地图的坐标转化为高德的坐标")
    @PostMapping("convert")
    public R<CoordinateVo> convert(@RequestBody ConvertParam convertParam) {
        CoordinateType coordinateFromType = CoordinateType.valueOf(convertParam.getFromType());
        CoordinateType coordinateToType = CoordinateType.valueOf(convertParam.getToType());
        CoordinateService coordinateService = EagleMapServiceFactory.getService(convertParam.getProvider(), CoordinateService.class);
        CoordinateVo result = coordinateService.convert(convertParam.getCoordinate(), coordinateFromType, coordinateToType);
        if (null == result) {
            return R.error("Convert failed.");
        }
        return R.success(result);
    }

}
