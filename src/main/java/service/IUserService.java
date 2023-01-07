package service;

import enums.LoginType;

public interface IUserService {

    void isUserRegistered(String loginId, LoginType loginType);
}
