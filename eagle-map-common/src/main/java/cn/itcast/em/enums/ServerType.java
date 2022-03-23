package cn.itcast.em.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ServerType {

    BAIDU(1, "Baidu"), AMAP(2, "AMap"), NONE(999, "none");

    @EnumValue
    private int value;
    private String desc;

    ServerType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

}
