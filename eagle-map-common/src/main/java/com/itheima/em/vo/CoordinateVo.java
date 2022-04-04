package com.itheima.em.vo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinateVo {

    @ApiModelProperty(value = "经度", required = true)
    private Double longitude; //经度
    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude; //纬度

    /**
     * 根据 经,纬度 字符串 实例化对象
     * 例如：116.343847,40.060539
     *
     * @param location 经纬度字符串
     */
    public CoordinateVo(String location) {
        String[] array = StrUtil.splitToArray(location, ',');
        this.longitude = Convert.toDouble(array[0]);
        this.latitude = Convert.toDouble(array[1]);
    }

    /**
     * 将经纬度数据用逗号连接
     *
     * @return
     */
    public String toParam() {
        return longitude + "," + latitude;
    }

}
