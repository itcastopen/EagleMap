package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import cn.itcast.em.vo.CoordinateVo;
import lombok.Data;

@Data
public class ConvertToGcj02Param {

    private ServerType provider = ServerType.NONE;
    private String fromType;
    private CoordinateVo[] coordinates;
}
