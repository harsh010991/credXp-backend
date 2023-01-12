package com.credXp.resource;

import com.credXp.dto.response.CredXpResponse;
import com.credXp.service.IUserService;
import com.credXp.utils.ResponseUtil;
import com.google.inject.Inject;
import com.credXp.dto.request.IsUserRegisteredRequest;
import com.credXp.dto.response.IsUserRegisteredResponse;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path(value = "/")
public class UserResource {

    @Inject
    private IUserService userService;

    @POST
    @Path(value = "isUserRegistered")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<String> isUserRegistered(IsUserRegisteredRequest isUserRegisteredRequest) {
        userService.isUserRegistered(isUserRegisteredRequest.getLoginId(), isUserRegisteredRequest.getLoginType());
        return ResponseUtil.createResponse("","Successfully send otp in mobile", true);
    }
}
