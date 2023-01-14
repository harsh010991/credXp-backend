package com.credXp.dto.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CredXpResponse<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private T data;
}
