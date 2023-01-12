package com.credXp.service.impl;

import com.credXp.bean.LoginInfo;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.enums.LoginType;
import com.credXp.pojo.OtpCachePojo;
import com.credXp.service.IGuavaCacheService;
import com.credXp.service.IUserService;
import com.credXp.utils.Utils;
import com.google.inject.Inject;
import com.credXp.enums.OTPType;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;

@Slf4j
public class UserService implements IUserService {
    @Inject
    private ILoginInfoDao loginInfoDao;

    @Inject
    private GuavaCacheService guavaCacheService;

    @Override
    public void isUserRegistered(String loginId, LoginType loginType) {
        if(loginType.equals(LoginType.MOBILE)) {
            Utils.validatePhoneNumber(loginId);
        }
        LoginInfo loginInfo = loginInfoDao.findById(loginId);
        if(loginInfo == null){
            OtpCachePojo otpCachePojo = OtpCachePojo.builder().otpType(OTPType.SIGN_UP).otp(Utils.generateOTP()).createdAt(Date.from(Instant.now())).build();
            //save in guava cache for signUp user
            guavaCacheService.putOtpPojo(loginId, otpCachePojo);
            log.info(String.valueOf(otpCachePojo.getOtp()));
            // send sms with signUp content.
            return;
        }
        //save in guava cache for login user
        OtpCachePojo otpCachePojo = OtpCachePojo.builder().otpType(OTPType.LOGIN).otp(Utils.generateOTP()).createdAt(Date.from(Instant.now())).build();
        // send sms with login content.
        guavaCacheService.putOtpPojo(loginId, otpCachePojo);
        log.info(String.valueOf(otpCachePojo.getOtp()));
    }
}
