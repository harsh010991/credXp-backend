package utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import javax.ws.rs.WebApplicationException;

import static constants.ErrorConstants.INVALID_PHONE_NUMBER;

public class Utils {

    public static boolean validatePhoneNumber(String phoneNumber){
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            if (!phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, "IN"))) {
                throw new WebApplicationException(INVALID_PHONE_NUMBER);
            }
        } catch (NumberParseException e) {
            throw new WebApplicationException(INVALID_PHONE_NUMBER);
        }
        return true;
    }
}
