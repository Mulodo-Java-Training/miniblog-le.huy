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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mini.systemvalue.SystemValue;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

/**
 * @author Le Dang Huy
 */
@Entity
@Table(name = "posts")
public class Posts
{

    @Id
    @Column(columnDefinition = "INT(20) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.AUTO)
    // for autonumber
    private int id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(2048)", nullable = false)
    private String content;

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

    @Column(columnDefinition = "TINYINT(1) default TRUE", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ForeignKey(name = "fk_posts_users")
    @JsonIgnore
    private Users user;

    @Transient
    private int userId;

    @OneToMany(targetEntity = Comments.class, mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> posts_comments;

    public Posts() {

    }

    public List<Comments> getPosts_comments()
    {

        return posts_comments;
    }

    public void setPosts_comments(List<Comments> posts_comments)
    {

        this.posts_comments = posts_comments;
    }

    public int getId()
    {

        return id;
    }

    public void setId(int id)
    {

        this.id = id;
    }

    public String getTitle()
    {

        return title;
    }

    public void setTitle(String title)
    {

        this.title = title;
    }

    public String getContent()
    {

        return content;
    }

    public void setContent(String content)
    {

        this.content = content;
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

    public boolean isStatus()
    {

        return status;
    }

    public void setStatus(boolean status)
    {

        this.status = status;
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

    public String getModified_At()
    {

        return new SimpleDateFormat(SystemValue.DATE_FORMAT).format(modified_at);
    }

    public void setModified_At(String modified_At)
    {

        Modified_At = modified_At;
    }

}
