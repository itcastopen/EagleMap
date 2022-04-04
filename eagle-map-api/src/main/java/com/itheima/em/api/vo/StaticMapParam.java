package com.itheima.em.api.vo;

import com.itheima.em.enums.ProviderType;
import com.itheima.em.vo.CoordinateVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class StaticMapParam {

    @ApiModelProperty(value = "服务提供商，必须大写，默认为：高德地图", example = "NONE")
    private ProviderType provider = ProviderType.NONE;
    @ApiModelProperty(value = "位置坐标", required = true)
    private CoordinateVo location;
    @ApiModelProperty(value = "图片的宽度，默认：750", example = "750")
    private Integer width = 750;
    @ApiModelProperty(value = "图片的宽度，默认：300", example = "300")
    private Integer height = 300;
    @ApiModelProperty(value = "地图缩放比，默认：10", example = "10")
    private Integer zoom = 10;
    @ApiModelProperty(value = "百度/高德的可选参数，如需要请根据官方文档添加参数<br/>" +
            "百度：https://lbsyun.baidu.com/index.php?title=static<br/>" +
            "高德：https://lbs.amap.com/api/webservice/guide/api/staticmaps")
    private Map<String, Object> param;


}
