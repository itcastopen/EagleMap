package cn.itcast.em.interceptor;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.itcast.em.exception.ServiceModeException;
import cn.itcast.em.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception ex) {
        log.error("出错了！ --> ", ex.fillInStackTrace());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(ServiceModeException.class)
    public R<String> ServiceModeExceptionHandler(ServiceModeException ex) {
        log.error("服务模式异常！ --> ", ex.fillInStackTrace());
        return R.error(ex.getMsg());
    }

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public R<String> noSuchBeanDefinitionHandler(Exception ex) {
        String message = ex.getMessage();
        if (ReUtil.contains("AMap.*Service", message)) {
            return R.error("The AMap service is not enabled");
        }
        if (ReUtil.contains("Baidu.*Service", message)) {
            return R.error("The Baidu service is not enabled");
        }
        log.error("出错了！ --> ", ex.fillInStackTrace());
        return R.error(ex.getMessage());
    }

}
