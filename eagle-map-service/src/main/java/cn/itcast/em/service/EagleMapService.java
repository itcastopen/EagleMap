package cn.itcast.em.service;

import cn.itcast.em.enums.ProviderType;
import org.springframework.core.Ordered;

public interface EagleMapService extends Ordered {

    ProviderType getProvider();
}
