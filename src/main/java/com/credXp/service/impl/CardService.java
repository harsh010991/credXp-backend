package com.credXp.service.impl;

import com.credXp.bean.Card;
import com.credXp.dao.ICardDao;
import com.credXp.pojo.CardPojo;
import com.credXp.service.ICardService;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class CardService implements ICardService {

    @Inject
    private ICardDao cardDao;

    @Override
    public List<CardPojo> getListOfCards() {
        List<Card> cardBeanList = cardDao.getListOfCards();
        List<CardPojo> cardPojos = new ArrayList<>();
        for (Card card : cardBeanList) {
            cardPojos.add(CardPojo.builder().id(card.getId()).cardType(card.getCardType()).name(card.getName()).status(card.getStatus()).createdAt(card.getUpdatedAt()).updatedAt(card.getUpdatedAt()).build());
        }
        return cardPojos;
    }
}
