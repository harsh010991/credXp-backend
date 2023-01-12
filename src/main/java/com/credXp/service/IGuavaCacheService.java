package com.credXp.service;

import com.credXp.pojo.OtpCachePojo;
import com.credXp.service.impl.GuavaCacheService;
import com.google.inject.ImplementedBy;

@ImplementedBy(GuavaCacheService.class)
public interface IGuavaCacheService {
     void putOtpPojo(String key , OtpCachePojo otpCachePojo);
}
