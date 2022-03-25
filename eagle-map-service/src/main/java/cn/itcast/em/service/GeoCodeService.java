package cn.itcast.em.service;

import cn.itcast.em.vo.GeoResultVO;

import java.util.Map;

/**
 * 地理/逆地理编码
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/25
 */
public interface GeoCodeService extends EagleMapService {

    /**
     * 将详细的结构化地址转换为经纬度坐标，例如：北京市昌平区回龙观街道传智播客办公楼
     *
     * @param address 详细地址
     * @param param   其他可选参数
     *                百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     *                高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#geo
     * @return 位置的geo信息
     */
    GeoResultVO geoCode(String address, Map<String, Object> param);

    /**
     * 将经纬度转换为详细结构化的地址，且返回附近周边的POI、AOI信息。例如：116.343847,40.060539
     *
     * @param longitude 经度
     * @param longitude 维度
     * @param param   其他可选参数
     *                百度地图：https://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     *                高德地图：https://lbs.amap.com/api/webservice/guide/api/georegeo#regeo
     * @return 位置的geo信息
     */
    GeoResultVO geoDecode(Double longitude, Double latitude, Map<String, Object> param);

}
