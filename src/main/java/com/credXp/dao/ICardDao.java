package com.credXp.dao;

import com.credXp.bean.Card;
import com.credXp.dao.impl.CardDao;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(CardDao.class)
public interface ICardDao {
    List<Card> getListOfCards();
}
