package com.itheima.em.admin.interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.itheima.em.admin.annotation.NoAuthorization;
import com.itheima.em.admin.utils.JwtUtils;
import com.itheima.em.admin.utils.UserThreadLocal;
import com.itheima.em.config.EagleConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一对请求的合法性进行校验
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private EagleConfig eagleConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(NoAuthorization.class)) {
            //不需要校验，直接放行
            return true;
        }

        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            response.setStatus(401);
            return false;
        }

        Map<String, Object> objectMap = JwtUtils.checkToken(token, this.eagleConfig.getAdmin().getPublicKey());
        if (null == objectMap) {
            response.setStatus(401);
            return false;
        }

        UserThreadLocal.set(Convert.toStr(objectMap.get("user")));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
