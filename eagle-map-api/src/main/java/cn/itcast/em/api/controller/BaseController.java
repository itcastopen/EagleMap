package cn.itcast.em.api.controller;

import cn.hutool.extra.spring.SpringUtil;
import cn.itcast.em.annotations.EagleAutowired;
import cn.itcast.em.enums.ServerType;
import cn.itcast.em.service.EagleMapService;

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
        switch (serverType) {
            case AMAP: {
                return this.getService(ServerType.AMAP.getDesc());
            }
            case BAIDU: {
                return this.getService(ServerType.BAIDU.getDesc());
            }
            default: {
                return this.t;
            }
        }
    }

    /**
     * 根据bean名称的前缀查找bean
     *
     * @param serviceNamePrefix 前缀，约定：百度->
     * @return
     */
    private T getService(String serviceNamePrefix) {
        //获取到泛型的类型
        String className = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //bean的名称规则：前缀+接口名称
        String name = serviceNamePrefix + clazz.getSimpleName();
        return (T) SpringUtil.getApplicationContext().getBean(name, clazz);
    }
}
