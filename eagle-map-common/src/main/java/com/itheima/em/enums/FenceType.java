package com.itheima.em.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum FenceType {

    CIRCLE(1, "圆形围栏"), POLYGON(2, "多边形围栏"), POLYLINE(3, "线形围栏"),
    DISTRICT(4, "行政区");

    @EnumValue
    private int value;
    private String desc;

    FenceType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

}
