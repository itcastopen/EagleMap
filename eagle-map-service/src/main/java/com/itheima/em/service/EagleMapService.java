package com.itheima.em.service;

import com.itheima.em.enums.ProviderType;
import org.springframework.core.Ordered;

public interface EagleMapService extends Ordered {

    ProviderType getProvider();
}
