package com.credXp.service;

import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.service.impl.UserCardDetailsService;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserCardDetailsService.class)
public interface IUserCardDetailsService {
    void saveUserCardDetails(UserCardDetailsReq userCardDetailsReq);
}
