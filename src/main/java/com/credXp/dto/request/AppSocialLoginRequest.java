package com.credXp.dto.request;

import lombok.Data;

@Data
public class AppSocialLoginRequest {
    private String googleAccessToken;
    private String email;
    private String firstname;
    private String lastName;
}
