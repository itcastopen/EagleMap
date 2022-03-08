package cn.itcast.em.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinateVo {

    private Double longitude; //经度
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
