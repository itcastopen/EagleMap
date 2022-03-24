package cn.itcast.em.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.itcast.em.config.EagleConfig;
import cn.itcast.em.enums.ProviderType;
import cn.itcast.em.service.EagleOrdered;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/24
 */
public class EagleMapServiceFactory {

    public static <T> T getService(ProviderType provider, Class<T> clazz) {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        EagleConfig eagleConfig = applicationContext.getBean(EagleConfig.class);
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        ProviderType finalProvider;
        if (provider == ProviderType.NONE) {
            //系统自动选择
            finalProvider = eagleConfig.getDefaultProviderStrategy();
        } else {
            finalProvider = provider;
        }

        if (finalProvider != ProviderType.NONE) {
            //根据参数查找具体的服务
            for (Map.Entry<String, T> entry : beans.entrySet()) {
                Object obj = ReflectUtil.invoke(entry.getValue(), "getProvider");
                if (ObjectUtil.equal(finalProvider, obj)) {
                    return (T) entry.getValue();
                }
            }
        } else {
            //根据order查找优先级最高的实现类
            //选择优先级高的实现
            Object bestObject = null;
            int ordered = EagleOrdered.LOWEST_PRECEDENCE;
            for (Object value : beans.values()) {
                int order = Convert.toInt(ReflectUtil.invoke(value, "getOrder"));
                if (order <= ordered) {
                    ordered = order;
                    bestObject = value;
                }
            }
            return (T) bestObject;
        }
        return null;
    }

}
