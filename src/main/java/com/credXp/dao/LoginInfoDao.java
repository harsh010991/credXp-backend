package com.credXp.dao;

import com.credXp.bean.LoginInfo;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.Getter;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.credXp.constants.LoggerConstant.CREATE_USER_REQUEST_DB_ERROR;

@Getter
@Setter
@EagerSingleton
@Slf4j
public class LoginInfoDao extends AbstractDAO<LoginInfo> implements ILoginInfoDao {

    @Inject
    public LoginInfoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public LoginInfo save(LoginInfo loginInfo) {
        try {
            currentSession().save(loginInfo);
            currentSession().flush();
            currentSession().refresh(loginInfo);
            return loginInfo;
        }catch (HibernateException he){
            log.error(CREATE_USER_REQUEST_DB_ERROR);
            log.error(he.getMessage(), he);
            throw new WebApplicationException(CREATE_USER_REQUEST_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LoginInfo findById(String id) {
        return (LoginInfo) currentSession().createQuery("from LoginInfo where id = :id")
                .setParameter("id", id).uniqueResult();
    }
}

