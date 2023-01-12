package com.credXp.pojo;

import com.credXp.enums.OTPType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OtpCachePojo {

    private OTPType otpType;
    private int otp;
    private Date createdAt;
}
