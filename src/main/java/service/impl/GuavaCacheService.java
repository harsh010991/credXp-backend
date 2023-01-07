package service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import pojo.OtpCachePojo;

import java.util.concurrent.TimeUnit;

public class GuavaCacheService {

    Cache<String, OtpCachePojo> otpCachePojo;

    @Inject
    GuavaCacheService(CredXpConfiguration credXpConfiguration){
        buildCache(credXpConfiguration);
    }

    private void buildCache(CredXpConfiguration configuration){
        otpCachePojo = CacheBuilder.newBuilder()
                .maximumSize(configuration.getGuavaCacheConfig().getMaxSessionCacheSize())
                .concurrencyLevel(configuration.getGuavaCacheConfig().getSessionCacheConcurrencyLevel())
                .expireAfterAccess(configuration.getGuavaCacheConfig().getSessionCacheExpiryInHours(), TimeUnit.HOURS)
                .recordStats()
                .build();
    }
}
