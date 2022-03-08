package cn.itcast.em.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ServerType {

    BAIDU(1, "baidu"), AMAP(2, "amap"), NONE(999, "none");

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
