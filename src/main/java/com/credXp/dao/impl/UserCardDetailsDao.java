package com.credXp.dao.impl;

import com.credXp.bean.UserCardDetails;
import com.credXp.dao.IUserCardDetailsDao;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.persistence.Entity;
import javax.persistence.NamedEntityGraph;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.credXp.constants.LoggerConstant.SAVE_USER_CARD_DETAILS_DB_ERROR;
import static com.credXp.constants.LoggerConstant.UPDATE_USER_CARD_DETAILS_DB_ERROR;

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
//            currentSession().refresh(userCardDetails);
            currentSession().clear();
        }catch (HibernateException he){
            log.error(SAVE_USER_CARD_DETAILS_DB_ERROR);
            log.error(he.getMessage(), he);
            throw new WebApplicationException(SAVE_USER_CARD_DETAILS_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

//    @Override
//    public void updateUserCardDetails(Integer totalCashSaved, DateTime updatedAt) {
//        try {
//          currentSession().createSQLQuery("U");
//        }catch (HibernateException he){
//            log.error(SAVE_USER_CARD_DETAILS_DB_ERROR);
//            log.error(he.getMessage(), he);
//            throw new WebApplicationException(UPDATE_USER_CARD_DETAILS_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    public UserCardDetails getUserCardDetails(int cardId, int accountId) {
        return (UserCardDetails) currentSession().createQuery(" from UserCardDetails where cardId = :cardId and accountId = :accountId").setParameter("cardId", cardId).setParameter("accountId", accountId).uniqueResult();
    }

    @Override
    public List<Object[]> getUserCardDetails(int accountId) {
//        return (List<UserCardDetails>)currentSession().createQuery(" from UserCardDetails  where accountId=:accountId").setParameter("accountId",accountId).list();
        return  currentSession().createSQLQuery("Select user_card_details.account_id, user_card_details.card_id, user_card_details.total_saving, user_card_details.status, card_list.name, card_list.offers, card_list.bank_name from user_card_details inner join card_list on user_card_details.card_id = card_list.id where user_card_details.account_id =:accountId").setParameter("accountId",accountId).list();
    }



}
