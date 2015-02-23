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
@Table(name = "comments")
public class Comments
{

    @Id
    @Column(columnDefinition = "INT(20) UNSIGNED", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    // for autonumber
    private int id;

    @Column(columnDefinition = "VARCHAR(254)", nullable = false)
    private String comment;

    @Column(columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date create_at;

    @Transient
    private String Create_At;

    @Column(columnDefinition = "TIMESTAMP(0)")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date modified_at;

    @Transient
    private String Modified_At;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ForeignKey(name = "fk_comments_users")
    @JsonIgnore
    private Users user;

    @Transient
    private int userId;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ForeignKey(name = "fk_comments_posts")
    @JsonIgnore
    private Posts post;

    @Transient
    private int postId;

    public Comments() {

    }

    public int getId()
    {

        return id;
    }

    public void setId(int id)
    {

        this.id = id;
    }

    public String getComment()
    {

        return comment;
    }

    public void setComment(String comment)
    {

        this.comment = comment;
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

    public Posts getPost()
    {

        return post;
    }

    public void setPost(Posts post)
    {

        this.post = post;
    }

    public int getPostId()
    {

        return post.getId();
    }

    public void setPostId(int postId)
    {

        this.postId = postId;
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
