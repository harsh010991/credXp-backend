package dto.response;

import lombok.Data;

@Data
public class CredXpResponse<I> {
    private Boolean success;
    private String message;
    private T data;
}
