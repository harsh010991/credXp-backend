package com.credXp.dao;

import com.credXp.bean.UserCardDetails;
import com.credXp.dao.impl.UserCardDetailsDao;
import com.google.inject.ImplementedBy;

import java.util.List;
import java.util.Objects;

@ImplementedBy(UserCardDetailsDao.class)
public interface IUserCardDetailsDao {

    void saveUserCardDetails(UserCardDetails userCardDetails);

    UserCardDetails getUserCardDetails(int cardId, int accountId);

    List<Object[]> getUserCardDetails(int accountId);

}
