package com.credXp.pojo;

import com.credXp.enums.CardType;
import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@Builder
public class CardPojo {
    private int id;
    private String name;
    private StatusType status;
    private CardType cardType;

    private JsonNode offers;
    private String bankName;
    private DateTime createdAt;
    private DateTime updatedAt;

    @JsonProperty("isCheck")
    private boolean isCheck;

}
