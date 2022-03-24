package cn.itcast.em.service;

import cn.itcast.em.enums.CoordinateType;
import cn.itcast.em.vo.CoordinateVo;

import java.util.List;

/**
 * 坐标相关的业务
 */
public interface CoordinateService extends EagleMapService {

    /**
     * 将指定的坐标转化为gcj02坐标体系
     *
     * @param fromType 原始坐标类型
     * @param froms    原始坐标
     * @return gcj02坐标值
     */
    List<CoordinateVo> convertToGcj02(CoordinateType fromType, CoordinateVo... froms);

    /**
     * 指定坐标体系转化
     *
     * @param from     原始坐标
     * @param fromType 原始坐标类型
     * @param toType   目标坐标类型 （不能转为wgs84）
     * @return 目标坐标
     */
    CoordinateVo convert(CoordinateVo from, CoordinateType fromType,
                         CoordinateType toType);
}
