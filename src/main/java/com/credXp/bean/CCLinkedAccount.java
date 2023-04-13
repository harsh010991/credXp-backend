package com.credXp.bean;

import com.credXp.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cc_linked_account")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CCLinkedAccount implements Serializable {
    public static final String CRITERIA_NAME_LOGIN_ID = "loginId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name="account_type", nullable = false)
    private String accountType;

    @Column(name = "access_token")
    private String accessToken;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusType status;

    @Column(name = "created_at", nullable = false)
    private DateTime createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private DateTime updatedAt;
}
