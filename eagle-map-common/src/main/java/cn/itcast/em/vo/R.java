package cn.itcast.em.vo;

import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 *
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = -4326147203336606257L;

    private Integer code; //编码：0成功，1和失败

    private String msg; //提示消息

    private T data; //数据

    public static <T> R<T> success() {
        return success(null);
    }

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 0;
        r.msg = "ok";
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(T object, String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 1;
        r.data = object;
        return r;
    }

    /**
     * 是否成功
     *
     * @return
     */
    @JsonIgnore
    public Boolean isSuccess() {
        return NumberUtil.equals(this.code, 0);
    }

}
