package com.credXp.service;

import com.credXp.dto.request.AddNewCardListDto;
import com.credXp.dto.request.NewCardDto;
import com.credXp.pojo.CardPojo;
import com.credXp.service.impl.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(CardService.class)
public interface ICardService {

    public List<CardPojo> getListOfCards();
    AddNewCardListDto addNewCards(AddNewCardListDto addNewCardListDto);
}
