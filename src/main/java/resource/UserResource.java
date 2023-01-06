package resource;

import dto.request.IsUserRegisteredRequest;
import dto.response.CredXpResponse;
import dto.response.IsUserRegisteredResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
@Slf4j
public class UserResource {

    @POST
    @Path("/isUserRegistered")
    public CredXpResponse<IsUserRegisteredResponse> isUserRegistered(IsUserRegisteredRequest isUserRegisteredRequest) {
        return null;
    }
}
