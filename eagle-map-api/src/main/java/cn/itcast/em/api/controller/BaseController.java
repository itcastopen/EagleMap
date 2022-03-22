package cn.itcast.em.api.controller;

import cn.hutool.extra.spring.SpringUtil;
import cn.itcast.em.annotations.EagleAutowired;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.service.EagleMapService;

import javax.xml.stream.events.Characters;
import java.lang.reflect.ParameterizedType;


public abstract class BaseController<T extends EagleMapService> {

    @EagleAutowired
    private T t;

    /**
     * 根据用户的serverType参数选择服务商，默认通过优先级选择
     *
     * @param serverType
     * @return
     */
    public T chooseService(ServerType serverType) {
        T t;
        switch (serverType) {
            case AMAP: {
                t = this.aMapService();
                break;
            }
            case BAIDU: {
                t = this.baiduService();
                break;
            }
            default: {
                t = this.defaultService();
                break;
            }
        }
        return t;
    }

    /**
     * 默认实现
     *
     * @return
     */
    public T defaultService() {
        return this.t;
    }

    /**
     * 百度实现
     *
     * @return
     */
    public T baiduService() {
        return this.getService("Baidu");
    }

    /**
     * 高德实现
     *
     * @return
     */
    public T aMapService() {
        return this.getService("AMap");
    }

    private T getService(String serviceNamePrefix) {
        //获取到泛型的类型
        String className = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String name = serviceNamePrefix + clazz.getSimpleName();
        return (T) SpringUtil.getApplicationContext().getBean(name, clazz);
    }
}
