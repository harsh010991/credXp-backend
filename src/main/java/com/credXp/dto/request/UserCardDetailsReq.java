package com.credXp.dto.request;

import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserCardDetailsReq {

    String email;
    List<UserCard> userCardList;
}
