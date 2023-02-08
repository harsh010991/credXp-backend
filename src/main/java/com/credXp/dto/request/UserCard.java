package com.credXp.dto.request;

import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCard {

    private int cardId;
    private int cashSaved;
    @JsonProperty("status")
    private StatusType statusType;
}
