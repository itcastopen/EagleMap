package cn.itcast.em.config;

import cn.itcast.em.enums.ProviderType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "eagle")
public class EagleConfig {

    private Integer timeout;
    private String baiduWebApi;
    private String baiduYingYanApi;
    private String amapWebApi;
    private String amapTsApi;
    private TraceImageConfig traceImage;
    private AdminConfig admin;
    private AMapServerConfig amap;
    private BaiduServerConfig baidu;
    private ProviderType defaultProviderStrategy = ProviderType.NONE;

}
