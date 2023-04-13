package com.credXp.service.impl;

import com.credXp.pojo.LoginTokenCache;
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
    Cache<String, LoginTokenCache> loginTokenCache;

    @Inject
    public GuavaCacheService(CredXpConfiguration credXpConfiguration){
            buildCache(credXpConfiguration);
    }

    private void buildCache(CredXpConfiguration configuration){
        otpCache = CacheBuilder.newBuilder()
                .maximumSize(configuration.getGuavaCacheConfig().getMaxCacheSize())
                .concurrencyLevel(configuration.getGuavaCacheConfig().getMaxConcurrencyLevel())
                .expireAfterAccess(configuration.getGuavaCacheConfig().getExpireAfterAccess(), TimeUnit.DAYS)
                .recordStats()
                .build();

        loginTokenCache = CacheBuilder.newBuilder()
                .maximumSize(configuration.getGuavaCacheConfig().getMaxCacheSize())
                .concurrencyLevel(configuration.getGuavaCacheConfig().getMaxConcurrencyLevel())
                .expireAfterAccess(configuration.getGuavaCacheConfig().getExpireAfterAccess(), TimeUnit.DAYS)
                .recordStats()
                .build();
    }

    public void putOtpPojo(String key , OtpCachePojo otpCachePojo){
        otpCache.put(key, otpCachePojo);
    }

    public OtpCachePojo getOtpCachePojo(String key){
        return otpCache.getIfPresent(key);
    }
    public void putLoginCachePojo(String key , LoginTokenCache loginTokenCachePoJo){
      loginTokenCache.put(key,loginTokenCachePoJo);
    }

    public LoginTokenCache getLoginCachePojo(String key){
        return loginTokenCache.getIfPresent(key);
    }
}
