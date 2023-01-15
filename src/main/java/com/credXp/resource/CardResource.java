package com.credXp.resource;

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

    @GET
    @Path("getCardList")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public CredXpResponse<List<CardPojo>> getCardList(){
        return ResponseUtil.createResponse(cardService.getListOfCards(), SUCCESS, true, 200);
    }
}
