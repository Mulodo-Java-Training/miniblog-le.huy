package mini.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mini.systemvalue.SystemValue;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Le Dang Huy
 */
@Entity
@Table(name = "users")
public class Users
{

    @Id
    @Column(columnDefinition = "INT(16) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "VARCHAR(32)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "VARCHAR(72)", nullable = false)
    @JsonIgnore
    private String password;

    @Column(columnDefinition = "VARCHAR(32)", nullable = false)
    private String lastname;

    @Column(columnDefinition = "VARCHAR(32)", nullable = false)
    private String firstname;

    @Column(columnDefinition = "VARCHAR(254)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date create_at;

    @Transient
    private String Create_At;

    @Column(columnDefinition = "TIMESTAMP(0) default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date modified_at;

    @Transient
    private String Modified_At;

    @OneToMany(targetEntity = Posts.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @Cascade(org.hibernate.annotations.CascadeType.) funny think inside ^^!
    @JsonIgnore
    private List<Posts> user_posts;

    @OneToMany(targetEntity = Comments.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> user_comments;

    @OneToMany(targetEntity = Token.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Token> user_token;

    public Users() {

    }

    public int getId()
    {

        return id;
    }

    public void setId(int id)
    {

        this.id = id;
    }

    public String getUsername()
    {

        return username;
    }

    public void setUsername(String username)
    {

        this.username = username;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(String password)
    {

        this.password = password;
    }

    public String getLastname()
    {

        return lastname;
    }

    public void setLastname(String lastname)
    {

        this.lastname = lastname;
    }

    public String getFirstname()
    {

        return firstname;
    }

    public void setFirstname(String firstname)
    {

        this.firstname = firstname;
    }

    public String getEmail()
    {

        return email;
    }

    public void setEmail(String email)
    {

        this.email = email;
    }

    public Date getCreate_at()
    {

        return create_at;
    }

    public void setCreate_at(Date create_at)
    {

        this.create_at = create_at;
    }

    public Date getModified_at()
    {

        return modified_at;
    }

    public void setModified_at(Date modified_at)
    {

        this.modified_at = modified_at;
    }

    public List<Posts> getUser_posts()
    {

        return user_posts;
    }

    public void setUser_posts(List<Posts> user_posts)
    {

        this.user_posts = user_posts;
    }

    public List<Comments> getUser_comments()
    {

        return user_comments;
    }

    public void setUser_comments(List<Comments> user_comments)
    {

        this.user_comments = user_comments;
    }

    public List<Token> getUser_token()
    {

        return user_token;
    }

    public void setUser_token(List<Token> user_token)
    {

        this.user_token = user_token;
    }

    public String getCreate_At()
    {

        return new SimpleDateFormat(SystemValue.DATE_FORMAT).format(create_at);
    }

    public void setCreate_At(String create_At)
    {

        Create_At = create_At;
    }

    public String getModified_At()
    {

        return new SimpleDateFormat(SystemValue.DATE_FORMAT).format(modified_at);
    }

    public void setModified_At(String modified_At)
    {

        Modified_At = modified_At;
    }
}
