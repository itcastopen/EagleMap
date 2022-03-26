package cn.itcast.em.mapper;

import cn.itcast.em.pojo.TraceServer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Mapper
public interface TraceServerMapper extends BaseMapper<TraceServer> {
}
