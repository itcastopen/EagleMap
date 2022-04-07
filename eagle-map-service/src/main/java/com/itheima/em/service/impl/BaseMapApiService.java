package com.itheima.em.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.itheima.em.config.EagleConfig;
import com.itheima.em.service.Function;
import com.itheima.em.service.MapApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Map;

/**
 * 通用http请求发送服务，如果需要修改默认实现，通过继承的方式覆盖此类中的方法即可
 */
@Slf4j
public abstract class BaseMapApiService implements MapApiService {

    @Autowired
    private EagleConfig eagleConfig;

    @Override
    @Retryable(value = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public <T> T execute(String url, Method method, Map<String, Object> param, Function<T, HttpResponse> function) {
        HttpRequest httpRequest;
        switch (method) {
            case GET: {
                httpRequest = HttpRequest.get(url);
                break;
            }
            case POST: {
                httpRequest = HttpRequest.post(url);
                break;
            }
            case PUT: {
                httpRequest = HttpRequest.put(url);
                break;
            }
            case DELETE: {
                httpRequest = HttpRequest.delete(url);
                break;
            }
            default: {
                return null;
            }
        }
        //设置请求参数
        setRequestHeader(httpRequest);

        //设置请求参数
        setRequestParam(httpRequest, param);

        try {
            //发起请求
            HttpResponse response = httpRequest
                    .timeout(Convert.toInt(this.eagleConfig.getTimeout(), 10000))//超时，毫秒
                    .execute();
            return function.callback(response);
        } catch (Exception e) {
            //出现错误，抛出异常进行重试
            String msg = StrUtil.format("Map service provider call failed! url = {}, method = {}, param = {}, msg = {}", url, method, param, e.getMessage());
            throw new RuntimeException(msg, e);
        }
    }

    @Recover //全部重试失败后执行
    public HttpResponse recover(RuntimeException e) {
        //重试全部失败的情况下记录日志
        log.error(e.getMessage(), e);
        return null; //返回默认
    }

    /**
     * 设置请求头, 默认实现form表单头，如果添加其他的头信息请覆盖实现此方法
     *
     * @param httpRequest
     */
    public void setRequestHeader(HttpRequest httpRequest) {
        httpRequest.header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded");
    }

    /**
     * 设置请求参数
     *
     * @param httpRequest
     * @param param
     */
    protected abstract void setRequestParam(HttpRequest httpRequest, Map<String, Object> param);

    public <T> T doGet(String url, Map<String, Object> param, Function<T, HttpResponse> function) {
        return this.execute(url, Method.GET, param, function);
    }

    public <T> T doPost(String url, Map<String, Object> param, Function<T, HttpResponse> function) {
        return this.execute(url, Method.POST, param, function);
    }

    public <T> T doPut(String url, Map<String, Object> param, Function<T, HttpResponse> function) {
        return this.execute(url, Method.PUT, param, function);
    }

    public <T> T doDelete(String url, Map<String, Object> param, Function<T, HttpResponse> function) {
        return this.execute(url, Method.DELETE, param, function);
    }
}
