package com.itheima.em.interceptor;

import cn.hutool.core.util.StrUtil;
import com.itheima.em.enums.ProviderType;
import com.itheima.em.util.ProviderThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zzj
 * @version 1.0
 */
@Component
public class ProviderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String providerStr = request.getHeader("provider");
        String provider = StrUtil.isNotEmpty(providerStr) ? providerStr : request.getParameter("provider");
        try {
            //如果成功获取到provider参数就将其放入到ThreadLocal中，如果没有获取到也忽略，
            // 主要是考虑到可能还有其他的方式传入
            ProviderThreadLocal.set(ProviderType.valueOf(provider));
        } catch (Exception e) {
            // e.printStackTrace();  Nothing needs to be done
        }
        return true;
    }
}
