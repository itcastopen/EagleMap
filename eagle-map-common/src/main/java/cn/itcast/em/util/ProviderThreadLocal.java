package cn.itcast.em.util;

import cn.itcast.em.enums.ProviderType;

/**
 * @author zzj
 * @version 1.0
 * @date 2022/3/26
 */
public final class ProviderThreadLocal {

    private static final ThreadLocal<ProviderType> LOCAL = new ThreadLocal<>();

    public static void set(ProviderType provider) {
        LOCAL.set(provider);
    }

    public static ProviderType get() {
        return LOCAL.get();
    }
}
