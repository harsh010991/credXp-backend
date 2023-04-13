package com.credXp.dao;

import com.credXp.bean.UserCardDetails;
import com.credXp.dao.impl.UserCardDetailsDao;
import com.google.inject.ImplementedBy;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Objects;

@ImplementedBy(UserCardDetailsDao.class)
public interface IUserCardDetailsDao {

    void saveUserCardDetails(UserCardDetails userCardDetails);

//    void updateUserCardDetails(Integer totalCashSaved, DateTime updatedAt);

    UserCardDetails getUserCardDetails(int cardId, int accountId);

    List<Object[]> getUserCardDetails(int accountId);

}
