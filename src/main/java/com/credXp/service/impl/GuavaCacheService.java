package com.credXp.service.impl;

import com.credXp.pojo.OtpCachePojo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.credXp.config.CredXpConfiguration;
import com.google.inject.Singleton;

import java.util.concurrent.TimeUnit;

@Singleton
public class GuavaCacheService {

    Cache<String, OtpCachePojo> otpCache;

    @Inject
    GuavaCacheService(CredXpConfiguration credXpConfiguration){
            buildCache(credXpConfiguration);
    }

    private void buildCache(CredXpConfiguration configuration){
        otpCache = CacheBuilder.newBuilder()
                .maximumSize(configuration.getGuavaCacheConfig().getMaxCacheSize())
                .concurrencyLevel(configuration.getGuavaCacheConfig().getMaxConcurrencyLevel())
                .expireAfterAccess(configuration.getGuavaCacheConfig().getExpireAfterAccess(), TimeUnit.SECONDS)
                .recordStats()
                .build();
    }

    public void putOtpPojo(String key , OtpCachePojo otpCachePojo){
        otpCache.put(key, otpCachePojo);
    }

    public OtpCachePojo getOtpCachePojo(String key){
        return otpCache.getIfPresent(key);
    }

}
