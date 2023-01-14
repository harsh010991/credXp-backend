package com.credXp.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerifyOtpResponse {
    private String token;
}
