package com.itheima.em.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;

import java.util.Map;

public interface MapApiService {

    <T> T execute(String url, Method method, Map<String, Object> param, Function<T, HttpResponse> function);

}
