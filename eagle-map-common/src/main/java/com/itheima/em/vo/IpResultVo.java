package com.itheima.em.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IpResultVo {

    @ApiModelProperty(value = "国家（或地区）", required = true)
    private String country;
    @ApiModelProperty(value = "省份", required = true)
    private String province;
    @ApiModelProperty(value = "城市", required = true)
    private String city;
    @ApiModelProperty(value = "区县", required = true)
    private String district;
    @ApiModelProperty(value = "运营商,如电信、联通、移动", required = true)
    private String isp;
    @ApiModelProperty(value = "经纬度", required = true)
    private String location;
    @ApiModelProperty(value = "IP地址", required = true)
    private String ip;
}
