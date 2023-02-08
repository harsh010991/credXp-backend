package com.credXp.pojo;

import lombok.Data;

@Data
public class OfferInfoPojo {
    private Double amount;
    private Integer rewardPoint;
    private String cardName;
    private String currency;
    private Double cashBack;
    private Boolean gift;
    private Double totalSaving;
    private String bankName;
}
