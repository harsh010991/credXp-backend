package com.credXp.service.impl;

import com.credXp.bean.Card;
import com.credXp.dao.ICardDao;
import com.credXp.dto.request.AddNewCardListDto;
import com.credXp.dto.request.NewCardDto;
import com.credXp.pojo.CardPojo;
import com.credXp.service.ICardService;
import com.google.inject.Inject;
import org.checkerframework.checker.units.qual.C;
import org.joda.time.DateTime;

import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public AddNewCardListDto addNewCards(AddNewCardListDto addNewCardListDto) {
        List<Card> cards = new ArrayList<>();
        for(NewCardDto newCardDto : addNewCardListDto.getNewCardList()){
            Card card = new Card();
            card.setCardType(newCardDto.getCardType());
            card.setName(newCardDto.getName());
            card.setStatus(newCardDto.getStatusType());
            card.setCreatedAt(DateTime.now());
            card.setUpdatedAt(DateTime.now());
            cards.add(card);
        }
        cardDao.addNewCardList(cards);
        return addNewCardListDto;
    }
}
