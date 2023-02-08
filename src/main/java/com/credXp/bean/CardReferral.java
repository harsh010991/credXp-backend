package com.credXp.bean;

import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "card_referral")
@NoArgsConstructor
public class CardReferral implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "link")
    private String referralLink;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "priority")
    private int priority;

    @Column(name = "cash_earned")
    private int cashEarned;

    @Column(name = "created_at", nullable = false)
    private DateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private DateTime updatedAt;

}
