package service.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import enums.LoginType;
import service.IUserService;
import utils.Utils;

import javax.ws.rs.WebApplicationException;

import static constants.ErrorConstants.INVALID_PHONE_NUMBER;

public class UserServiceImpl implements IUserService {
    @Override
    public void isUserRegistered(String loginId, LoginType loginType) {
        if(loginType.equals(LoginType.MOBILE)) {
            Utils.validatePhoneNumber(loginId);
        }
    }
}
