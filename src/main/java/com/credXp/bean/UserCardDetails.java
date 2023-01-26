package com.credXp.bean;

import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_card_details")
@Data
public class UserCardDetails implements Serializable {

    @Id
    @JsonProperty("account_id")
    private int accountId;

    @JsonProperty("card_id")
    private int cardId;

    @JsonProperty("cash_saved")
    private int cashSaved;

    @JsonProperty("status")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @JsonProperty("created_At")
    private DateTime createdAt;

    @JsonProperty("updated_at")
    private DateTime updatedAt;
}
