package com.credXp.resource;

import com.credXp.annotations.RequiredAuthIdentifier;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.dto.response.CredXpResponse;
import com.credXp.dto.response.VerifyOtpResponse;
import com.credXp.service.IUserCardDetailsService;
import com.credXp.utils.ResponseUtil;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

import static com.credXp.constants.MessageConstant.SUCCESS;

@Path("/")
public class UserCardResource {

    @Inject
    private IUserCardDetailsService userCardDetailsService;

    @POST
    @Path("/userCard")
    @UnitOfWork
    @RequiredAuthIdentifier
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<String> saveUserCardDetails(UserCardDetailsReq userCardDetailsReq){
        userCardDetailsService.saveUserCardDetails(userCardDetailsReq);
        return ResponseUtil.createResponse(null, SUCCESS, true, 200 );
    }
}
