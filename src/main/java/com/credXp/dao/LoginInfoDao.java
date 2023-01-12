package com.credXp.dao;

import com.credXp.bean.LoginInfo;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.inject.Inject;
import javax.inject.Named;

@Getter
@Setter
@EagerSingleton
public class LoginInfoDao extends AbstractDAO<LoginInfo> implements ILoginInfoDao {

    @Inject
    public LoginInfoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public LoginInfo save(LoginInfo loginInfo) {
        return persist(loginInfo);
    }

    @Override
    public LoginInfo findById(String id) {
        return (LoginInfo) currentSession().createQuery("from LoginInfo where id = :id")
                .setParameter("id", id).uniqueResult();
    }
}

