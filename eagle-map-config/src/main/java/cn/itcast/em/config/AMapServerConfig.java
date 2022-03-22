package cn.itcast.em.config;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties(prefix = "eagle.amap")
@ConditionalOnProperty(name = "eagle.amap.enable", havingValue = "true")
public class AMapServerConfig {

    private MapServerConfig server;
    private MapServerConfig browser;
    private Boolean enable;
    private String name;

    @PostConstruct
    public void check() {
        if (ObjectUtil.isAllEmpty(this.server, this.browser)) {
            throw new RuntimeException("Invalid configuration of AMap.");
        }
    }

}
