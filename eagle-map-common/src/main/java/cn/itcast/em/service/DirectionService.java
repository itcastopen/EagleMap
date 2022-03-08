package cn.itcast.em.service;

import cn.itcast.em.vo.CoordinateVo;

import java.util.Map;

/**
 * 路线规划
 */
public interface DirectionService extends EagleMapService{

    /**
     * 驾车路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t5
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-0
     * @return 百度/高德响应的数据
     */
    String driving(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param);

    /**
     * 步行路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t6
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-2
     * @return 百度/高德响应的数据
     */
    String walking(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param);

    /**
     * 骑行路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t7
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-1
     * @return 百度/高德响应的数据
     */
    String bicycling(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param);

    /**
     * 公交路线规划
     *
     * @param origin      起点经纬度
     * @param destination 目的地经纬度
     * @param param       百度/高德的可选参数，如需要请根据官方文档添加参数
     *                    高德：https://lbs.amap.com/api/webservice/guide/api/newroute#t9
     *                    百度：https://lbsyun.baidu.com/index.php?title=webapi/directionlite-v1#service-page-anchor-1-3
     * @return 百度/高德响应的数据
     */
    // String transit(CoordinateVo origin, CoordinateVo destination, Map<String, Object> param);
}
