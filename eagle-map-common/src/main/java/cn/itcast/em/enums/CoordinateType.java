package cn.itcast.em.enums;

public enum CoordinateType {

    BAIDU("百度(bd09ll)", 5, "baidu"),
    AMAP("高德(gcj02)", 3, "autonavi"),
    QQ("腾讯(gcj02)", 3, "autonavi"),
    GPS("wgs84", 1, "gps"),
    SOU_GOU("搜狗", 2, "autonavi"),
    EAGLE("EagleMap", 3, "autonavi");

    private String name;
    private int baiduValue;
    private String aMapValue;

    CoordinateType(String name, int baiduValue, String aMapValue) {
        this.name = name;
        this.baiduValue = baiduValue;
        this.aMapValue = aMapValue;
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

    @Override
    public String toString() {
        return this.name;
    }

}
