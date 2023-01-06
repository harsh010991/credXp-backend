package utils;

import dto.response.CredXpResponse;

public class ResponseUtil {
        public static CredXpResponse<T> createResponse(T data, String message, Boolean success){
            CredXpResponse<T> credXpResponse = new CredXpResponse<I>();
            credXpResponse.setData(data);
            credXpResponse.setSuccess(success);
            credXpResponse.setMessage(message);
            return credXpResponse;
        }
}
