package cn.itcast.em.config;

import cn.hutool.core.util.ObjectUtil;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.enums.ServiceMode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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
    private ServiceMode serviceMode = ServiceMode.BASE;

    @PostConstruct
    public void check() {
        if (ObjectUtil.isAllEmpty(this.amap, this.baidu)) {
            throw new RuntimeException("Configure at least one map service provider！");
        }
    }

}
