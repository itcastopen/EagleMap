package cn.itcast.em.api.vo;

import cn.itcast.em.enums.ServerType;
import cn.itcast.em.vo.CoordinateVo;
import lombok.Data;

import java.util.Map;

@Data
public class StaticMapParam {

    private ServerType provider = ServerType.NONE;
    private CoordinateVo location;
    private Integer width = 750;
    private Integer height = 300;
    private Integer zoom = 10;
    private Map<String, Object> param;


}
