package cn.itcast.em.vo;

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
     * 将经纬度数据用逗号连接
     *
     * @return
     */
    public String toParam() {
        return longitude + "," + latitude;
    }

}
