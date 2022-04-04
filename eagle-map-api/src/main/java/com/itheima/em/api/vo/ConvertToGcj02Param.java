package com.itheima.em.api.vo;

import com.itheima.em.enums.ProviderType;
import com.itheima.em.vo.CoordinateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ConvertToGcj02Param {

    @ApiModelProperty(value = "服务提供商，必须大写，默认为：百度地图", example = "NONE")
    private ProviderType provider = ProviderType.NONE;
    @ApiModelProperty(value = "原坐标类型，可用参数（必须大写）：BAIDU、AMAP、QQ、GPS、SOU_GOU、EAGLE", required = true, example = "BAIDU")
    private String fromType;
    @ApiModelProperty(value = "坐标数据", required = true)
    private CoordinateVo[] coordinates;
}
