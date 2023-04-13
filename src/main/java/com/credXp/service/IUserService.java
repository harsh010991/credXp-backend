package com.credXp.service;

import com.credXp.dto.request.AppSocialLoginRequest;
import com.credXp.dto.request.OfferDetailsRequest;
import com.credXp.enums.LoginType;
import com.credXp.enums.OTPType;
import com.credXp.service.impl.UserService;
import com.google.inject.ImplementedBy;
import org.apache.commons.lang3.tuple.Pair;

@ImplementedBy(UserService.class)
public interface IUserService {

    Pair<Integer, String> isUserRegistered(String loginId, LoginType loginType);

    String verifyOtp(String loginId, int countryCode, OTPType otpType, int otp);

    boolean validateSessionToken(String token);

    Integer appSocialLogin(AppSocialLoginRequest appSocialLoginRequest);

}
