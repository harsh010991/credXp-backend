package com.credXp.bean;

import com.credXp.enums.CardType;
import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "card_list")
@NoArgsConstructor
public class Card implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CardType cardType;

    @Column(name = "offers", nullable = false)
    private String offers;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "created_at", nullable = false)
    private DateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private DateTime updatedAt;
}
