package cn.itcast.em.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ProviderType {

    BAIDU(1, "BAIDU", "百度地图"),
    AMAP(2, "AMAP", "高德地图"),
    QQ(3, "QQ", "腾讯地图"),
    NONE(999, "NONE", "默认地图");

    @EnumValue
    private int value;
    private String name;
    private String desc;

    ProviderType(int value, String name, String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

}
