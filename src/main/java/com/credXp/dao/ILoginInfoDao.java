package com.credXp.dao;

import com.credXp.bean.LoginInfo;
import com.google.inject.ImplementedBy;
import io.dropwizard.hibernate.UnitOfWork;

@ImplementedBy(LoginInfoDao.class)
public interface ILoginInfoDao {

    LoginInfo save(LoginInfo loginInfo);


    LoginInfo findById(String id);

}
