package com.credXp.dao.impl;

import com.credXp.bean.Card;
import com.credXp.dao.ICardDao;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.credXp.constants.LoggerConstant.CREATE_USER_REQUEST_DB_ERROR;
import static com.credXp.constants.LoggerConstant.GET_ALL_CARD_LIST_DB_ERROR;

@EagerSingleton
@Slf4j
public class CardDao extends AbstractDAO<Card> implements ICardDao {

    @Inject
    public CardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Card> getListOfCards() {
        try {
            return list(currentSession().createQuery("from Card"));
        }
       catch (HibernateException he){
           log.error(GET_ALL_CARD_LIST_DB_ERROR);
           log.error(he.getMessage(), he);
           throw new WebApplicationException(GET_ALL_CARD_LIST_DB_ERROR, Response.Status.INTERNAL_SERVER_ERROR);
       }
    }
}
