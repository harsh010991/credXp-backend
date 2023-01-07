package dto.response;

import lombok.Data;

@Data
public class CredXpResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}
