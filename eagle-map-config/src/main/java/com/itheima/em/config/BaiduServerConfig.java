package com.itheima.em.config;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "eagle.baidu")
@Component
@ConditionalOnProperty(name = "eagle.baidu.enable", havingValue = "true")
public class BaiduServerConfig {

    private MapServerConfig server;
    private MapServerConfig browser;
    private List<TraceServerConfig> traceServers = Collections.emptyList();
    private Boolean enable;
    private String name;

    @PostConstruct
    public void check() {
        if (ObjectUtil.isAllEmpty(this.server, this.browser)) {
            throw new RuntimeException("Invalid configuration of Baidu.");
        }
    }

}
