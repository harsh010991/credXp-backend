package com.credXp.dto.request;

import com.credXp.enums.CardType;
import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewCardDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private StatusType statusType;

    @JsonProperty("type")
    private CardType cardType;

}
