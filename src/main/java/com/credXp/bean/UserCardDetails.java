package com.credXp.bean;

import com.credXp.enums.StatusType;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_card_details")
@Data
@NamedEntityGraph(name = "user_card_details_entity_graph", attributeNodes = @NamedAttributeNode("card"))
public class UserCardDetails implements Serializable {

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "card_id", insertable = false, updatable = false)
    private int cardId;

    @Column(name = "total_saving")
    private int totalSaving;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column(name = "created_At")
    private DateTime createdAt;

    @Column(name = "updated_at")
    private DateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

}
