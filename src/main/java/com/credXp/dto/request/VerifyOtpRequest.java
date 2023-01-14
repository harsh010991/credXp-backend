package com.credXp.dto.request;

import com.credXp.enums.OTPType;
import lombok.Data;


@Data
public class VerifyOtpRequest {
    private String loginId;
    private int countryCode;
    private int otp;
    private OTPType otpType;
}
