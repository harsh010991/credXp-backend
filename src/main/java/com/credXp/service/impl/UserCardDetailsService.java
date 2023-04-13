package com.credXp.service.impl;

import com.credXp.bean.Card;
import com.credXp.bean.CardReferral;
import com.credXp.bean.LoginInfo;
import com.credXp.bean.UserCardDetails;
import com.credXp.dao.ICardReferralDao;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.dao.IUserCardDetailsDao;
import com.credXp.dto.request.OfferDetailsReq;
import com.credXp.dto.request.OfferDetailsRequest;
import com.credXp.dto.request.UserCard;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.pojo.CardPojo;
import com.credXp.pojo.OfferInfoPojo;
import com.credXp.pojo.ReferralCardPojo;
import com.credXp.pojo.UserCardInfoPojo;
import com.credXp.service.ICardService;
import com.credXp.service.IUserCardDetailsService;
import com.credXp.service.IUserService;
import com.credXp.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.mysql.cj.jdbc.Clob;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.CLOB;
import oracle.sql.ClobDBAccess;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

import static com.credXp.constants.ErrorConstants.INVALID_LOGIN_ID;

@Slf4j
public class UserCardDetailsService implements IUserCardDetailsService {

    @Inject
    private IUserCardDetailsDao userCardDetailsDao;

    @Inject
    private ILoginInfoDao loginInfoDao;

    @Inject
    ICardService cardService;

    @Inject
    ICardReferralDao cardReferralDao;

    @Inject
    IUserService userService;

    @Override
    public void saveUserCardDetails(UserCardDetailsReq userCardDetailsReq) {
        if(StringUtils.isBlank(userCardDetailsReq.getEmail())){
            log.error("EmailId shouldn't be empty for {}", userCardDetailsReq.getEmail());
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        LoginInfo loginInfo = loginInfoDao.findById(userCardDetailsReq.getEmail());
        if(loginInfo == null){
            log.error("No account found for this emailId {}", userCardDetailsReq.getEmail());
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.INTERNAL_SERVER_ERROR);
        }
        int accountId = loginInfo.getAccountId();
        List<UserCard> userCardList = userCardDetailsReq.getUserCardList();
        if (userCardList.isEmpty()) {
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        log.info(UserCardDetailsReq.class.getName() + " : ", userCardDetailsReq);
        for (UserCard userCard : userCardList) {
            UserCardDetails userCardDetails = userCardDetailsDao.getUserCardDetails(userCard.getCardId(), accountId);
            if (userCardDetails == null) {
                userCardDetails = new UserCardDetails();
                userCardDetails.setTotalSaving(userCard.getCashSaved());
                userCardDetails.setStatusType(userCard.getStatusType());
                userCardDetails.setCardId(userCard.getCardId());
                userCardDetails.setCreatedAt(DateTime.now());
                userCardDetails.setAccountId(accountId);
                userCardDetails.setUpdatedAt(DateTime.now());
                userCardDetailsDao.saveUserCardDetails(userCardDetails);
            }
        }
    }

    @Override
    public void calculateOffers(OfferDetailsReq offerDetailsReq) {
        int amount = offerDetailsReq.getAmount();
        String place = offerDetailsReq.getPlace();
//        List<CardPojo> cardPojoList = cardService.getListOfCards();

    }

    @Override
    public UserCardInfoPojo getExistingOfferDetails(OfferDetailsRequest offerDetailsRequest) {
        if(StringUtils.isBlank(offerDetailsRequest.getEmail())){
            log.error("EmailId shouldn't be empty for {}", offerDetailsRequest.getEmail());
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        LoginInfo loginInfo = loginInfoDao.findById(offerDetailsRequest.getEmail());
        if(loginInfo == null){
            log.error("No account found for this emailId {}", offerDetailsRequest.getEmail());
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.INTERNAL_SERVER_ERROR);
        }
        UserCardInfoPojo userCardInfoPojo = new UserCardInfoPojo();
        try {
            List<Object[]> userCardDetailsList = userCardDetailsDao.getUserCardDetails(loginInfo.getAccountId());
            Set<String> offerSet = new HashSet<>();
            Map<String, List<OfferInfoPojo>> cardOfferMap = new HashMap<>();
            Set<String> userCardBankNameSet = new HashSet<>();
            for (Object[] userCardDetails : userCardDetailsList) {
//                Clob clob = (Clob) userCardDetails[5];
//                StringBuilder sb = new StringBuilder();
//                try (Reader reader = clob.getCharacterStream();
//                     BufferedReader br = new BufferedReader(reader)) {
//                    String line;
//                    while(null != (line = br.readLine())) {
//                        sb.append(line);
//                    }
//                } catch (Exception e) {
//                    //handle error
//                    log.error(e.getMessage(), e);
//                }
//                String json = sb.toString();
                JsonNode offers = Utils.mapper.readTree(userCardDetails[5].toString());
                for (JsonNode offerNode : offers.get("inclusion")) {
                    String offerName = offerNode.get("key").asText();
                    offerSet.add(offerName);
                    OfferInfoPojo offerInfoPojo = new OfferInfoPojo();
                    offerInfoPojo.setAmount(offerNode.get("value").get("amount").asDouble());
                    offerInfoPojo.setRewardPoint(offerNode.get("value").get("rewardPoint").asDouble());
                    offerInfoPojo.setGift(offers.get("gift").asBoolean());
                    offerInfoPojo.setCurrency(offers.get("currency").asText());
                    offerInfoPojo.setCashBack(offers.get("cashBack").asDouble());
                    List<OfferInfoPojo> offerInfoPojoList;
                    if (cardOfferMap.containsKey(offerName)) {
                        offerInfoPojoList = cardOfferMap.get(offerName);
                    } else {
                        offerInfoPojoList = new ArrayList<>();
                    }
                    offerInfoPojoList.add(offerInfoPojo);
                    cardOfferMap.put(offerName, offerInfoPojoList);
                    offerInfoPojo.setTotalSaving(Double.parseDouble(userCardDetails[2].toString()));
                    offerInfoPojo.setBankName(userCardDetails[6].toString());
                    offerInfoPojo.setCardName(userCardDetails[4].toString());
                    userCardBankNameSet.add(offerInfoPojo.getBankName());
                }
            }
            List<CardReferral> cardReferralList = cardReferralDao.getCardReferralList();
            List<ReferralCardPojo> referralCardPojoList = new ArrayList<>();
            for(CardReferral cardReferral : cardReferralList){
                if(!userCardBankNameSet.contains(cardReferral.getCardName())){
                    ReferralCardPojo referralCardPojo = ReferralCardPojo.builder().referralCardName(cardReferral.getCardName())
                            .referralLink(cardReferral.getReferralLink()).cashBackPercentage(cardReferral.getCashBackPercentage()).build();
                    referralCardPojoList.add(referralCardPojo);
                }
            }
            userCardInfoPojo.setCardOfferMap(cardOfferMap);
            userCardInfoPojo.setReferralCardPojoList(referralCardPojoList);
            List<String> offersList = new ArrayList<>(offerSet);
            Collections.sort(offersList);
            userCardInfoPojo.setOfferSet(offersList);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return userCardInfoPojo;
    }
}
