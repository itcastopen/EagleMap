package cn.itcast.em.enums;

public enum CoordinateType {

    BAIDU("百度(bd09ll)", 5, "baidu", "bd09ll"),
    AMAP("高德(gcj02)", 3, "autonavi", "gcj02"),
    QQ("腾讯(gcj02)", 3, "autonavi", "gcj02"),
    GPS("wgs84", 1, "gps", "wgs84"),
    SOU_GOU("搜狗", 2, "autonavi", "gcj02"),
    EAGLE("EagleMap", 3, "autonavi", "gcj02");

    private String name;
    private int baiduValue;
    private String aMapValue;
    private String value;

    CoordinateType(String name, int baiduValue, String aMapValue, String value) {
        this.name = name;
        this.baiduValue = baiduValue;
        this.aMapValue = aMapValue;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getBaiduValue() {
        return baiduValue;
    }

    public String getaMapValue() {
        return aMapValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
