package com.credXp.service;

import com.credXp.dto.request.OfferDetailsReq;
import com.credXp.dto.request.OfferDetailsRequest;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.pojo.UserCardInfoPojo;
import com.credXp.service.impl.UserCardDetailsService;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserCardDetailsService.class)
public interface IUserCardDetailsService {
    void saveUserCardDetails(UserCardDetailsReq userCardDetailsReq);

    void calculateOffers(OfferDetailsReq offerDetailsReq);

    UserCardInfoPojo getExistingOfferDetails(OfferDetailsRequest offerDetailsRequest);
}
