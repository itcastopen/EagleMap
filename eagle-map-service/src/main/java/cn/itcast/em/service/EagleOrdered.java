package cn.itcast.em.service;

public interface EagleOrdered {

    int HIGHEST_PRECEDENCE = -2147483648; //最高优先级
    int LOWEST_PRECEDENCE = 2147483647; //最低优先级
    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
    int FOUR = 4;
    int FIVE = 5;

    int getOrder();
}