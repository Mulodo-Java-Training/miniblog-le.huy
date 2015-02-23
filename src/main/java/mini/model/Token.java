package mini.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mini.systemvalue.SystemValue;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

/**
 * @author Le Dang Huy
 */
@Entity
@Table(name = "token")
public class Token
{

    @Id
    @Column(columnDefinition = "INT(16) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.AUTO)
    // for autonumber
    @JsonIgnore
    private int id;

    @Column(columnDefinition = "VARCHAR(64)", nullable = false, unique = true)
    private String access_token;

    @Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date create_at;

    @Transient
    private String Create_At;

    @Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date expired;

    @Transient
    private String ExpiredTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ForeignKey(name = "fk_token_users")
    @JsonIgnore
    private Users user;

    @Transient
    private int userId;

    public Token() {

    }

    public int getId()
    {

        return id;
    }

    public void setId(int id)
    {

        this.id = id;
    }

    public String getAccess_token()
    {

        return access_token;
    }

    public void setAccess_token(String access_token)
    {

        this.access_token = access_token;
    }

    public Date getCreate_at()
    {

        return create_at;
    }

    public void setCreate_at(Date create_at)
    {

        this.create_at = create_at;
    }

    public Date getExpired()
    {

        return expired;
    }

    public void setExpired(Date expired)
    {

        this.expired = expired;
    }

    public Users getUser()
    {

        return user;
    }

    public void setUser(Users user)
    {

        this.user = user;
    }

    public int getUserId()
    {

        return user.getId();
    }

    public void setUserId(int userId)
    {

        this.userId = userId;
    }

    public String getCreate_At()
    {

        return new SimpleDateFormat(SystemValue.DATE_FORMAT).format(create_at);
    }

    public void setCreate_At(String create_At)
    {

        Create_At = create_At;
    }

    public String getExpiredTime()
    {

        return new SimpleDateFormat(SystemValue.DATE_FORMAT).format(expired);
    }

    public void setExpiredTime(String expiredTime)
    {

        ExpiredTime = expiredTime;
    }

}
