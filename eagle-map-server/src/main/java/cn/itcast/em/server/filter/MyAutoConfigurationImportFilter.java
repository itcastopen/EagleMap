package cn.itcast.em.server.filter;

import cn.hutool.core.util.StrUtil;
import cn.itcast.em.enums.ServiceMode;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 自动配置的过滤器，目的是在 BASE 模式下将 DataSourceAutoConfiguration 过滤掉，所有与数据库相关的配置将不再加载
 *
 * @author zzj
 * @version 1.0
 */
public class MyAutoConfigurationImportFilter implements AutoConfigurationImportFilter, EnvironmentAware {

    private ServiceMode serviceMode;

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        if (autoConfigurationClasses.length == 1) {
            return new boolean[]{true};
        }
        boolean[] result = new boolean[autoConfigurationClasses.length];
        String dataSourceName = DataSourceAutoConfiguration.class.getName();
        for (int i = 0; i < autoConfigurationClasses.length; i++) {
            if (serviceMode == ServiceMode.BASE) {
                if (StrUtil.equals(autoConfigurationClasses[i], dataSourceName)) {
                    result[i] = false;
                } else {
                    result[i] = true;
                }
            } else {
                result[i] = true;
            }
        }
        return result;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.serviceMode = ServiceMode.valueOf(environment.getProperty("eagle.service-mode"));
    }
}
