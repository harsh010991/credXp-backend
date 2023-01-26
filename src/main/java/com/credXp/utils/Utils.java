package com.credXp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.apache.commons.lang3.RandomUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.credXp.constants.CredXpConstants.END_OTP_NUMBER;
import static com.credXp.constants.CredXpConstants.START_OTP_NUMBER;
import static com.credXp.constants.ErrorConstants.INVALID_PHONE_NUMBER;

public class Utils {

    public static ObjectMapper mapper = new ObjectMapper();

    public static boolean validatePhoneNumber(String phoneNumber){
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            if (!phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, "IN"))) {
                throw new WebApplicationException(INVALID_PHONE_NUMBER, Response.Status.BAD_REQUEST);
            }
        } catch (NumberParseException e) {
            throw new WebApplicationException(INVALID_PHONE_NUMBER, Response.Status.BAD_REQUEST);
        }
        return true;
    }

    public static String getCacheKey(String key){
        return "CXP_" + key;
    }

    public static int generateOTP() {
        return RandomUtils.nextInt(START_OTP_NUMBER, END_OTP_NUMBER);
    }
}
