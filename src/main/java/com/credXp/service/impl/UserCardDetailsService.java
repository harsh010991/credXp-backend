package com.credXp.service.impl;

import com.credXp.bean.LoginInfo;
import com.credXp.bean.UserCardDetails;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.dao.IUserCardDetailsDao;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.service.IUserCardDetailsService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.credXp.constants.ErrorConstants.INVALID_LOGIN_ID;

@Slf4j
public class UserCardDetailsService implements IUserCardDetailsService {

    @Inject
    private IUserCardDetailsDao userCardDetailsDao;

    @Inject
    private ILoginInfoDao loginInfoDao;

    @Override
    public void saveUserCardDetails(UserCardDetailsReq userCardDetailsReq) {
        if(userCardDetailsReq == null){
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        log.info(UserCardDetailsReq.class.getName() + " : ", userCardDetailsReq);
        UserCardDetails userCardDetails = userCardDetailsDao.getUserCardDetails(userCardDetailsReq.getCardId());
        if(userCardDetails != null){
            userCardDetails.setCashSaved(userCardDetails.getCashSaved());
        }
        else {
            LoginInfo loginInfo = loginInfoDao.findById(userCardDetailsReq.getLoginId());
            if(loginInfo == null){
                throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.NOT_FOUND);
            }
            log.info(LoginInfo.class.getName() + " : ", loginInfo);
            userCardDetails = new UserCardDetails();
            userCardDetails.setCashSaved(userCardDetails.getCashSaved());
            userCardDetails.setStatusType(userCardDetails.getStatusType());
            userCardDetails.setCardId(userCardDetailsReq.getCardId());
            userCardDetails.setCreatedAt(DateTime.now());
            userCardDetails.setAccountId(userCardDetails.getAccountId());
        }
        userCardDetails.setUpdatedAt(DateTime.now());
        userCardDetailsDao.saveUserCardDetails(userCardDetails);
    }
}
