package com.itheima.em.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * 服务模式的异常
 *
 * @author zzj
 * @version 1.0
 */
@Builder
@Getter
public class ServiceModeException extends RuntimeException {

    private String msg;

    public ServiceModeException(String msg) {
        this.msg = msg;
    }
}
