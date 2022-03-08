package cn.itcast.em.server.spring;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.itcast.em.annotations.EagleAutowired;
import cn.itcast.em.api.controller.BaseController;
import cn.itcast.em.service.EagleOrdered;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 该类配置@EagleAutowired注解完成自定义对象的注入，有2种情况：
 * 1、如果在Spring容器中只有一个实现，直接注入即可
 * 2、如果有多个实现，会根据order值进行选择，值越小越优先
 */
@Component
public class EagleBeanProcessor implements BeanPostProcessor, PriorityOrdered {

    @Override
    public int getOrder() {
        //最低优先级，确保容器中所有的bean实例化完成后进行注入的操作
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof BaseController)) {
            return bean;
        }
        Class<?> cls = bean.getClass();
        for (Field declaredField : cls.getSuperclass().getDeclaredFields()) {
            EagleAutowired annotation = declaredField.getAnnotation(EagleAutowired.class);
            if (annotation == null) {
                continue;
            }

            //获取到泛型的类型
            String className = ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("");
            }

            //如果有属性包含了@EagleAutowired注解，需要从容器中获取bean将其注入
            Map<String, ?> beans = SpringUtil.getApplicationContext().getBeansOfType(clazz);
            if (CollUtil.isEmpty(beans) && annotation.required()) {
                throw new RuntimeException("Missing implementation class for " + declaredField.getType().getName());
            }

            if (CollUtil.isEmpty(beans) && !annotation.required()) {
                break;
            }

            if (beans.size() == 1) {
                //只有一个实现类，无需选择，直接注入
                for (Object value : beans.values()) {
                    ReflectUtil.setFieldValue(bean, declaredField, value);
                }
            } else {
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
                ReflectUtil.setFieldValue(bean, declaredField, bestObject);
            }
        }
        return bean;
    }

}
