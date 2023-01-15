package com.credXp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddNewCardListDto {

    @JsonProperty("newCardList")
    private List<NewCardDto> newCardList;
}
