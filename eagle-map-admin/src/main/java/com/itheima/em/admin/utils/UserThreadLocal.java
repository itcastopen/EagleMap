package com.itheima.em.admin.utils;

public class UserThreadLocal {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {

    }

    /**
     * 将用户id放到ThreadLocal中
     *
     * @param userId
     */
    public static void set(String userId) {
        LOCAL.set(userId);
    }

    /**
     * 从ThreadLocal中获取用户
     */
    public static String get() {
        return LOCAL.get();
    }

    /**
     * 从当前线程中删除用户
     */
    public static void remove() {
        LOCAL.remove();
    }

}
