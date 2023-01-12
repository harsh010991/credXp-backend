package com.credXp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GuavaCacheConfig {

    @JsonProperty("max.cache.size")
    private int maxCacheSize;

    @JsonProperty("max.concurrency.level")
    private int maxConcurrencyLevel;

    @JsonProperty("expire.after.access")
    private int expireAfterAccess;
}
