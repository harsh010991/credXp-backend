package com.credXp.service;

import com.credXp.enums.LoginType;
import com.credXp.service.impl.UserService;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserService.class)
public interface IUserService {

    void isUserRegistered(String loginId, LoginType loginType);
}
