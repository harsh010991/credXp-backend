package bean;

import enums.LoginType;
import enums.StatusType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class LoginInfo implements Serializable {

    public static final String CRITERIA_NAME_LOGIN_ID = "loginId";

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "account_id", length = 10, nullable = false)
    private Integer accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", length = 20, nullable = false)
    private LoginType loginType;

    @Column(name = "country_code",columnDefinition = "SMALLINT(6)", nullable = false)
    private Integer countryCode;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusType status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}