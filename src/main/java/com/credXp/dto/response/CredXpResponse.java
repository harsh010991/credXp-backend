package com.credXp.dto.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CredXpResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}
