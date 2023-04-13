package com.credXp.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReferralCardPojo {

    private String referralLink;
    private String referralCardName;
    private Double cashBackPercentage;
}
