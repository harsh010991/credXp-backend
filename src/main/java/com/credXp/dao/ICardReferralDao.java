package com.credXp.dao;

import com.credXp.bean.CardReferral;
import com.credXp.dao.impl.CardReferralDao;
import com.google.inject.ImplementedBy;

import java.util.List;


@ImplementedBy(CardReferralDao.class)
public interface ICardReferralDao {

    List<CardReferral> getCardReferralList();
}
