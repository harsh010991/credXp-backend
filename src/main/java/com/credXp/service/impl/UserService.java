package com.credXp.service.impl;

import com.credXp.bean.LoginInfo;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.enums.LoginType;
import com.credXp.enums.StatusType;
import com.credXp.pojo.LoginTokenCache;
import com.credXp.pojo.OtpCachePojo;
import com.credXp.service.IUserService;
import com.credXp.utils.Utils;
import com.google.inject.Inject;
import com.credXp.enums.OTPType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;

import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static com.credXp.constants.ErrorConstants.*;
import static com.credXp.constants.MessageConstant.LOGIN_UP_OTP_SUCCESS_MSG;
import static com.credXp.constants.MessageConstant.SIGN_UP_OTP_SUCCESS_MSG;

@Slf4j
public class UserService implements IUserService {
    @Inject
    private ILoginInfoDao loginInfoDao;

    @Inject
    private GuavaCacheService guavaCacheService;

    @Override
    public Pair<Integer, String> isUserRegistered(String loginId, LoginType loginType) {
        if (loginType.equals(LoginType.MOBILE)) {
            Utils.validatePhoneNumber(loginId);
        }
        LoginInfo loginInfo = loginInfoDao.findById(loginId);
        OtpCachePojo otpCachePojo = guavaCacheService.getOtpCachePojo(loginId);
        if (loginInfo == null) {
            if(otpCachePojo == null || otpCachePojo.getOtpType() != OTPType.SIGN_UP) {
                 otpCachePojo = OtpCachePojo.builder().otpType(OTPType.SIGN_UP).otp(Utils.generateOTP()).createdAt(Date.from(Instant.now())).build();
                //save in guava cache for signUp user
                guavaCacheService.putOtpPojo(loginId, otpCachePojo);
            }
            log.info(String.valueOf(otpCachePojo.getOtp()));
            // send sms with signUp content.
            return Pair.of(4404, SIGN_UP_OTP_SUCCESS_MSG);
        }
        //save in guava cache for login user
        if(otpCachePojo == null || otpCachePojo.getOtpType() != OTPType.LOGIN) {
             otpCachePojo = OtpCachePojo.builder().otpType(OTPType.LOGIN).otp(Utils.generateOTP()).createdAt(Date.from(Instant.now())).build();
            guavaCacheService.putOtpPojo(loginId, otpCachePojo);
        }
        // send sms with login content.
        log.info(String.valueOf(otpCachePojo.getOtp()));
        return Pair.of(4200, LOGIN_UP_OTP_SUCCESS_MSG);
    }

    @Override
    public String verifyOtp(String loginId, int countryCode, OTPType otpType, int otp) {
        String token = null;
        if (otpType == OTPType.SIGN_UP) {
            OtpCachePojo signUpOtpPojo = guavaCacheService.getOtpCachePojo(loginId);
            validateOtp(signUpOtpPojo, otpType, otp);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(loginId);
            loginInfo.setLoginType(LoginType.MOBILE);
            loginInfo.setStatus(StatusType.ACTIVE);
            loginInfo.setCountryCode(countryCode);
            loginInfo.setCreatedAt(DateTime.now());
            loginInfo.setUpdatedAt(DateTime.now());
            loginInfo = loginInfoDao.save(loginInfo);
            return generateLoginToken(loginId, loginInfo.getAccountId());
        } else {
            LoginInfo loginInfo = loginInfoDao.findById(loginId);
            if (loginInfo == null) {
                throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
            }
            OtpCachePojo signUpOtpPojo = guavaCacheService.getOtpCachePojo(loginId);
            validateOtp(signUpOtpPojo, otpType, otp);
            return generateLoginToken(loginId, loginInfo.getAccountId());
        }

    }

    @Override
    public boolean validateSessionToken(String token) {
        LoginTokenCache loginTokenCache = guavaCacheService.getLoginCachePojo(token);
        return loginTokenCache == null || loginTokenCache.getExpiryTime().isBefore(DateTime.now());
    }

    public String generateLoginToken(String loginId, int accountId) {
        String token = UUID.randomUUID().toString() + '-' + UUID.randomUUID().toString();
        System.out.println(token);
        LoginTokenCache loginTokenCache = LoginTokenCache.builder().accountId(accountId).expiryTime(DateTime.now().toDateTime()).loginId(loginId).createdAt(DateTime.now()).build();
        guavaCacheService.putLoginCachePojo(token, loginTokenCache);
        return token;
    }

    private void validateOtp(OtpCachePojo otpCachePojo, OTPType otpType, int otp) {
        if(otpCachePojo == null){
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        if (!otpCachePojo.getOtpType().equals(otpType)) {
            throw new WebApplicationException(INVALID_OTP_TYPE, Response.Status.UNAUTHORIZED);
        }
        if (otp != otpCachePojo.getOtp()) {
            throw new WebApplicationException(INVALID_OTP, Response.Status.UNAUTHORIZED);
        }
    }
}
