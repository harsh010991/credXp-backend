package com.credXp.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class UserCardInfoPojo {
    private List<String> offerSet;
    private Map<String,List<OfferInfoPojo>> cardOfferMap;

    private List<ReferralCardPojo> referralCardPojoList;
}
