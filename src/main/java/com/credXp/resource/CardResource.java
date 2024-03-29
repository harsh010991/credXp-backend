package com.credXp.resource;

import com.credXp.dto.request.AddNewCardListDto;
import com.credXp.dto.request.AppSocialLoginRequest;
import com.credXp.dto.response.CredXpResponse;
import com.credXp.pojo.CardPojo;
import com.credXp.service.ICardService;
import com.credXp.utils.ResponseUtil;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static com.credXp.constants.MessageConstant.SUCCESS;

@Slf4j
@Path(value = "/")
public class CardResource {

    @Inject
    private ICardService cardService;

    @POST
    @Path("getCardList")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<List<CardPojo>> getCardList(AppSocialLoginRequest appSocialLoginRequest){
        return ResponseUtil.createResponse(cardService.getListOfCards(appSocialLoginRequest), SUCCESS, true, 200);
    }

    @POST
    @Path("addNewCard")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<AddNewCardListDto> addNewCard(AddNewCardListDto addNewCardListDto) {
        return ResponseUtil.createResponse(cardService.addNewCards(addNewCardListDto), SUCCESS, true, 200);
    }
}
