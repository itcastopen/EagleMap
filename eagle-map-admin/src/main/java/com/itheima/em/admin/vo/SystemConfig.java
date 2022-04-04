package com.itheima.em.admin.vo;

import com.itheima.em.config.MapServerConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/18
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemConfig {

    private String serverName;
    private Boolean enable;
    private List<MapServerConfig> servers;

}
