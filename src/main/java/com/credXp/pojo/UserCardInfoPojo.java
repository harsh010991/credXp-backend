package com.credXp.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class UserCardInfoPojo {
    private Set<String> offerSet;
    private Map<String,List<OfferInfoPojo>> cardOfferMap;

    private String referralLink;
    private String referralCardName;
    private Double cashBackPercentage;
}
