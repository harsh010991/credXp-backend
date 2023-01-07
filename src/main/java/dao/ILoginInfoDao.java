package dao;

import bean.LoginInfo;
import com.google.inject.ImplementedBy;
import exceptions.HibernateDaoException;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Map;

@ImplementedBy(LoginInfoDao.class)
public interface ILoginInfoDao {

    LoginInfo save(LoginInfo loginInfo);


    LoginInfo findById(String id);

}
