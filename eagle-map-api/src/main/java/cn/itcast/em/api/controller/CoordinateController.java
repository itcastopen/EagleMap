package cn.itcast.em.api.controller;

import cn.hutool.core.collection.CollUtil;
import cn.itcast.em.api.vo.ConvertParam;
import cn.itcast.em.api.vo.ConvertToGcj02Param;
import cn.itcast.em.enums.CoordinateType;
import cn.itcast.em.service.CoordinateService;
import cn.itcast.em.vo.CoordinateVo;
import cn.itcast.em.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/coordinate")
public class CoordinateController extends BaseController<CoordinateService> {

    /**
     * 将指定的坐标转化为gcj02坐标体系
     *
     * @param coordinateVos 原始坐标类型与原始坐标
     * @return gcj02坐标值
     */
    @PostMapping("convert/gcj02")
    public R<List<CoordinateVo>> convertToGcj02(@RequestBody ConvertToGcj02Param coordinateVos) {
        CoordinateType coordinateType = CoordinateType.valueOf(coordinateVos.getFromType());
        List<CoordinateVo> list = super.chooseService(coordinateVos.getProvider()).convertToGcj02(coordinateType, coordinateVos.getCoordinates());
        if (CollUtil.isEmpty(list)) {
            return R.error("Convert to gcj02 failed.");
        }
        return R.success(list);
    }

    /**
     * 指定坐标体系转化
     *
     * @param convertVo 坐标转化参数
     * @return 目标坐标
     */
    @PostMapping("convert")
    public R<CoordinateVo> convert(@RequestBody ConvertParam convertVo) {
        CoordinateType coordinateFromType = CoordinateType.valueOf(convertVo.getFromType());
        CoordinateType coordinateToType = CoordinateType.valueOf(convertVo.getToType());
        CoordinateVo result = super.chooseService(convertVo.getProvider()).convert(convertVo.getCoordinate(), coordinateFromType, coordinateToType);
        if (null == result) {
            return R.error("Convert failed.");
        }
        return R.success(result);
    }

}
