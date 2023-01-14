package com.credXp.bean;

import com.credXp.enums.LoginType;
import com.credXp.enums.StatusType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "login_info")
@NoArgsConstructor
public class LoginInfo implements Serializable {

    public static final String CRITERIA_NAME_LOGIN_ID = "loginId";

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "account_id", length = 10, nullable = false)
    @GeneratedValue
    private Integer accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", length = 20, nullable = false)
    private LoginType loginType;

    @Column(name = "country_code",columnDefinition = "SMALLINT(6)", nullable = false)
    private Integer countryCode;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusType status;

    @Column(name = "created_at", nullable = false)
    private DateTime createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private DateTime updatedAt;

}
