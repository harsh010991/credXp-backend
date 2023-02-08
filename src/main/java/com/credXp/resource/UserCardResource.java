package com.credXp.resource;

import com.credXp.annotations.RequiredAuthIdentifier;
import com.credXp.dto.request.OfferDetailsReq;
import com.credXp.dto.request.UserCardDetailsReq;
import com.credXp.dto.response.CredXpResponse;
import com.credXp.dto.response.VerifyOtpResponse;
import com.credXp.pojo.UserCardInfoPojo;
import com.credXp.service.IUserCardDetailsService;
import com.credXp.utils.ResponseUtil;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;

import static com.credXp.constants.CredXpConstants.ACCOUNT_ID;
import static com.credXp.constants.MessageConstant.SUCCESS;

@Path("/user")
public class UserCardResource {

    @Inject
    private IUserCardDetailsService userCardDetailsService;

    @POST
    @Path("/card")
    @UnitOfWork
//    @RequiredAuthIdentifier
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<String> saveUserCardDetails(@NotNull UserCardDetailsReq userCardDetailsReq, @Context ContainerRequestContext req){
        userCardDetailsService.saveUserCardDetails(userCardDetailsReq, Integer.parseInt(req.getHeaderString(ACCOUNT_ID)));
        return ResponseUtil.createResponse(null, SUCCESS, true, 200 );
    }

    @POST
    @Path("/offerDetails")
//    @RequiredAuthIdentifier
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<UserCardInfoPojo> getOffersDetails()
    {
        return ResponseUtil.createResponse(userCardDetailsService.getExistingOfferDetails(3), SUCCESS, true, 200);
    }
}
