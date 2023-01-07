package dao;

import bean.LoginInfo;
import exceptions.HibernateDaoException;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Getter
@Setter
public class LoginInfoDao extends AbstractDAO<LoginInfo> implements ILoginInfoDao {

    @Inject
    public LoginInfoDao(@Named("sessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public LoginInfo save(LoginInfo loginInfo) {
        return persist(loginInfo);
    }

    @Override
    public LoginInfo findById(String id) {
        return get(id);
    }
}

