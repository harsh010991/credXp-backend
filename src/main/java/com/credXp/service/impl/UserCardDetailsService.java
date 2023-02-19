package com.credXp.service.impl;

import com.credXp.bean.Card;
import com.credXp.bean.CardReferral;
import com.credXp.bean.UserCardDetails;
import com.credXp.dao.ICardReferralDao;
import com.credXp.dao.ILoginInfoDao;
import com.credXp.dao.IUserCardDetailsDao;
import com.credXp.dto.request.OfferDetailsReq;
import com.credXp.dto.request.UserCard;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.pojo.CardPojo;
import com.credXp.pojo.OfferInfoPojo;
import com.credXp.pojo.UserCardInfoPojo;
import com.credXp.service.ICardService;
import com.credXp.service.IUserCardDetailsService;
import com.credXp.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.util.*;

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

    @Override
    public void saveUserCardDetails(UserCardDetailsReq userCardDetailsReq, int accountId) {
        List<UserCard> userCardList = userCardDetailsReq.getUserCardList();
        if (userCardList.isEmpty()) {
            throw new WebApplicationException(INVALID_LOGIN_ID, Response.Status.BAD_REQUEST);
        }
        log.info(UserCardDetailsReq.class.getName() + " : ", userCardDetailsReq);
        for (UserCard userCard : userCardList) {
            UserCardDetails userCardDetails = userCardDetailsDao.getUserCardDetails(userCard.getCardId(), accountId);
            if (userCardDetails != null) {
                userCardDetails.setTotalSaving(userCard.getCashSaved());
            } else {
                userCardDetails = new UserCardDetails();
                userCardDetails.setTotalSaving(userCard.getCashSaved());
                userCardDetails.setStatusType(userCard.getStatusType());
                userCardDetails.setCardId(userCard.getCardId());
                userCardDetails.setCreatedAt(DateTime.now());
                userCardDetails.setAccountId(accountId);
            }
            userCardDetails.setUpdatedAt(DateTime.now());
            userCardDetailsDao.saveUserCardDetails(userCardDetails);
        }
    }

    @Override
    public void calculateOffers(OfferDetailsReq offerDetailsReq) {
        int amount = offerDetailsReq.getAmount();
        String place = offerDetailsReq.getPlace();
        List<CardPojo> cardPojoList = cardService.getListOfCards();

    }

    @Override
    public UserCardInfoPojo getExistingOfferDetails(int accountId) {
        UserCardInfoPojo userCardInfoPojo = new UserCardInfoPojo();
        try {
            List<Object[]> userCardDetailsList = userCardDetailsDao.getUserCardDetails(accountId);
            Set<String> offerSet = new HashSet<>();
            Map<String, List<OfferInfoPojo>> cardOfferMap = new HashMap<>();
            Set<String> userCardBankNameSet = new HashSet<>();
            for (Object[] userCardDetails : userCardDetailsList) {
                JsonNode offers = Utils.mapper.readTree(userCardDetails[5].toString());
                for (JsonNode offerNode : offers.get("inclusion")) {
                    String offerName = offerNode.get("key").asText();
                    offerSet.add(offerName);
                    OfferInfoPojo offerInfoPojo = new OfferInfoPojo();
                    offerInfoPojo.setAmount(offerNode.get("value").get("amount").asDouble());
                    offerInfoPojo.setRewardPoint(offerNode.get("value").get("rewardPoint").asInt());
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
            for(CardReferral cardReferral : cardReferralList){
                if(!userCardBankNameSet.contains(cardReferral.getCardName())){
                    userCardInfoPojo.setReferralCardName(cardReferral.getCardName());
                    userCardInfoPojo.setReferralLink(cardReferral.getReferralLink());
                    userCardInfoPojo.setCashBackPercentage(cardReferral.getCashBackPercentage());
                    break;
                }
            }
            userCardInfoPojo.setCardOfferMap(cardOfferMap);
            userCardInfoPojo.setOfferSet(offerSet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userCardInfoPojo;
    }
}
