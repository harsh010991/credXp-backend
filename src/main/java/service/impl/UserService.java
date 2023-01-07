package service.impl;

import bean.LoginInfo;
import com.google.inject.Inject;
import dao.ILoginInfoDao;
import enums.LoginType;
import service.IUserService;
import utils.Utils;

public class UserService implements IUserService {
    private ILoginInfoDao loginInfoDao;

    @Inject
    UserService(ILoginInfoDao loginInfoDao){
        this.loginInfoDao=loginInfoDao;
    }
    @Override
    public void isUserRegistered(String loginId, LoginType loginType) {
        if(loginType.equals(LoginType.MOBILE)) {
            Utils.validatePhoneNumber(loginId);
        }
        LoginInfo loginInfo = loginInfoDao.findById(loginId);
        if(loginInfo == null){
            //save in guava cache for signUp user
            // send sms with signUp content.
        }
        //save in guava cache for login user
        // send sms with login content.
        return;
    }
}
