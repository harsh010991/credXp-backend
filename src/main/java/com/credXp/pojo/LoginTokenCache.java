package com.credXp.pojo;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@Builder
public class LoginTokenCache {
    private String loginId;
    private DateTime expiryTime;

    private DateTime createdAt;
    private int accountId;
}
