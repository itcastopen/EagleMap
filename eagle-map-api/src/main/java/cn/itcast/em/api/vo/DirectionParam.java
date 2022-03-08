package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import cn.itcast.em.vo.CoordinateVo;
import lombok.Data;

import java.util.Map;

@Data
public class DirectionParam {

    private ServerType provider = ServerType.NONE;
    private CoordinateVo origin;
    private CoordinateVo destination;
    private Map<String, Object> param;


}
