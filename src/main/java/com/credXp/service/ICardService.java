package com.credXp.service;

import com.credXp.pojo.CardPojo;
import com.credXp.service.impl.CardService;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(CardService.class)
public interface ICardService {

    public List<CardPojo> getListOfCards();
}
