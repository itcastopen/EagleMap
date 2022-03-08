package cn.itcast.em.service;

import cn.itcast.em.vo.CoordinateVo;

import java.util.Map;

/**
 * 静态图相关服务
 */
public interface StaticMapService extends EagleMapService{

    /**
     * 静态图服务
     *
     * @param location 中心点坐标。
     * @param width    图片的宽度
     * @param height   图片的高度
     * @param zoom     地图缩放级别:[1,17]
     * @param param    百度/高德的可选参数，如需要请根据官方文档添加参数
     *                 百度：https://lbsyun.baidu.com/index.php?title=static
     *                 高德：https://lbs.amap.com/api/webservice/guide/api/staticmaps
     * @return base64图片数据
     */
    String query(CoordinateVo location, Integer width,
                 Integer height, Integer zoom, Map<String, Object> param);
}
