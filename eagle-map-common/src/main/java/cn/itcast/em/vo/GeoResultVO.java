package cn.itcast.em.vo;

import lombok.Data;

/**
 * 位置的geo信息
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/25
 */
@Data
public class GeoResultVO {

    private String address; //详情地址
    private String country; //国家
    private String province; //省
    private String city; //市
    private String district; //区
    private CoordinateVo location; //所在的经纬度
    private String data; //地图服务商返回的json数据

}
