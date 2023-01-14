package com.credXp.resource;

import com.credXp.dto.request.VerifyOtpRequest;
import com.credXp.dto.response.CredXpResponse;
import com.credXp.dto.response.VerifyOtpResponse;
import com.credXp.service.IUserService;
import com.credXp.utils.ResponseUtil;
import com.google.inject.Inject;
import com.credXp.dto.request.IsUserRegisteredRequest;
import com.credXp.dto.response.IsUserRegisteredResponse;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.credXp.constants.MessageConstant.SUCCESS;

@Slf4j
@Path(value = "/")
public class UserResource {

    @Inject
    private IUserService userService;

    @POST
    @UnitOfWork
    @Path(value = "isUserRegistered")
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<String> isUserRegistered(IsUserRegisteredRequest isUserRegisteredRequest) {
        Pair<Integer, String> resPair = userService.isUserRegistered(isUserRegisteredRequest.getLoginId(), isUserRegisteredRequest.getLoginType());
        return ResponseUtil.createResponse(null, resPair.getRight(), true, resPair.getLeft());
    }

    @POST
    @UnitOfWork
    @Path(value = "verify/otp")
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<VerifyOtpResponse> verifyOtp(VerifyOtpRequest verifyOtpRequest){
           String token =  userService.verifyOtp(verifyOtpRequest.getLoginId(),verifyOtpRequest.getCountryCode(), verifyOtpRequest.getOtpType(), verifyOtpRequest.getOtp());
           return ResponseUtil.createResponse(VerifyOtpResponse.builder().token(token).build(), SUCCESS, true, 200 );
    }
}
