package com.credXp.utils;

import com.credXp.dto.response.CredXpResponse;

public class ResponseUtil {
        public static <T> CredXpResponse<T> createResponse(T data, String message, boolean success, int statusCode){
            CredXpResponse<T> credXpResponse = new CredXpResponse<T>();
            credXpResponse.setData(data);
            credXpResponse.setSuccess(success);
            credXpResponse.setMessage(message);
            credXpResponse.setStatusCode(statusCode);
            return credXpResponse;
        }
}
