package cn.itcast.em.vo;

import lombok.Data;

@Data
public class IpResultVo {
    private String country; //国家（或地区）
    private String province; //省份
    private String city; //城市
    private String district; //区县
    private String isp; //运营商,如电信、联通、移动
    private String location; //经纬度
    private String ip; //IP地址
}
