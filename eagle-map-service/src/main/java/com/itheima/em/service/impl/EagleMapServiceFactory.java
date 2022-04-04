package com.itheima.em.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.enums.ServiceMode;
import com.itheima.em.exception.EagleMapException;
import com.itheima.em.exception.ServiceModeException;
import com.itheima.em.service.EagleOrdered;
import com.itheima.em.util.ProviderThreadLocal;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * 服务工厂，通过该工厂可以找出 provider 对应的服务提供者的
 * 如果传入的 provider 找不到会抛出 EagleMapException 异常
 *
 * @author zzj
 * @version 1.0
 * @date 2022/3/24
 */
public class EagleMapServiceFactory {

    public static <T> T getService(Class<T> clazz) {
        return getService(ProviderThreadLocal.get(), clazz);
    }

    public static <T> T getService(ProviderType provider, Class<T> clazz) {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        EagleConfig eagleConfig = applicationContext.getBean(EagleConfig.class);
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        if (CollUtil.isEmpty(beans) && eagleConfig.getServiceMode() == ServiceMode.BASE) {
            //没有任何实现类，服务模式在BASE下，但是调用了 COMPLETE 下的实现
            throw new ServiceModeException("EagleMap's current service-mode is 'BASE', please set the service mode to 'COMPLETE'!");
        }
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

        //返回默认提供者
        throw new EagleMapException(StrUtil.format("The specified provider ({}) was not found. ", provider));
    }

}
