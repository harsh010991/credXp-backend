package com.credXp.dao.impl;

import com.credXp.bean.UserCardDetails;
import com.credXp.dao.IUserCardDetailsDao;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.credXp.constants.LoggerConstant.SAVE_USER_CARD_DETAILS_DB_ERROR;

@Slf4j
@EagerSingleton
public class UserCardDetailsDao extends AbstractDAO<UserCardDetails> implements IUserCardDetailsDao {

    @Inject
    public UserCardDetailsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void saveUserCardDetails(UserCardDetails userCardDetails) {
        try {
            currentSession().save(userCardDetails);
            currentSession().flush();
            currentSession().refresh(userCardDetails);
        }catch (HibernateException he){
            log.error(SAVE_USER_CARD_DETAILS_DB_ERROR);
            log.error(he.getMessage(), he);
            throw new WebApplicationException(SAVE_USER_CARD_DETAILS_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserCardDetails getUserCardDetails(int cardId) {
        return (UserCardDetails) currentSession().createQuery(" from UserCardDetails where cardId = :cardId").setParameter("cardId", cardId).uniqueResult();
    }
}