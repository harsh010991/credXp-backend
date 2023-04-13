package com.credXp.dao;

import com.credXp.bean.CCLinkedAccount;
import com.credXp.bean.UserCardDetails;
import com.credXp.dao.impl.CCLinkedAccountDao;
import com.credXp.dao.impl.CardReferralDao;
import com.google.inject.ImplementedBy;

@ImplementedBy(CCLinkedAccountDao.class)
public interface ICCLinkedAccountDao {
     void saveCCLinkedAccount(CCLinkedAccount ccLinkedAccount);
     CCLinkedAccount getCCLinkedAccount(String accessToken);
}
