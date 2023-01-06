package dto.request;

import enums.LoginType;
import lombok.Data;

@Data
public class IsUserRegisteredRequest {
    private String loginId;
    private String countryCode;
    private LoginType loginType;
}
