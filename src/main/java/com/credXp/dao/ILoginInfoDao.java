package com.credXp.dao;

import com.credXp.bean.LoginInfo;
import com.credXp.dao.impl.LoginInfoDao;
import com.google.inject.ImplementedBy;

@ImplementedBy(LoginInfoDao.class)
public interface ILoginInfoDao {

    LoginInfo save(LoginInfo loginInfo);


    LoginInfo findById(String id);

}
