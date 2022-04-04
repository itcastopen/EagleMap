package com.itheima.em.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/10
 */
@Builder
@Getter
public class EagleMapException extends RuntimeException {

    private String msg;

    public EagleMapException(String msg) {
        this.msg = msg;
    }
}
