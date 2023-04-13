package com.credXp.dao.impl;

import com.credXp.bean.CCLinkedAccount;
import com.credXp.bean.UserCardDetails;
import com.credXp.dao.ICCLinkedAccountDao;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.credXp.constants.LoggerConstant.SAVE_CC_LINKED_ACCOUNT_DB_ERROR;

@Slf4j
@EagerSingleton
public class CCLinkedAccountDao extends AbstractDAO<CCLinkedAccount> implements ICCLinkedAccountDao {
    @Inject
    public CCLinkedAccountDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public void saveCCLinkedAccount(CCLinkedAccount ccLinkedAccount) {
        try {
            currentSession().save(ccLinkedAccount);
            currentSession().flush();
            currentSession().clear();
        }catch (HibernateException he){
            log.error(SAVE_CC_LINKED_ACCOUNT_DB_ERROR);
            log.error(he.getMessage(), he);
            throw new WebApplicationException(SAVE_CC_LINKED_ACCOUNT_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CCLinkedAccount getCCLinkedAccount(String accessToken) {
        return (CCLinkedAccount) currentSession().createQuery(" from CCLinkedAccount where accessToken = :accessToken").setParameter("accessToken", accessToken).uniqueResult();
    }
}
