package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.model.Comments;
import mini.model.Posts;
import mini.model.Users;
import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 *
 */

public class CommentCreateForm
{

    @NotNull
    @FormParam ( "postid" )
    public int postid;

    @NotNull
    @Pattern ( regexp = SystemValue.COMMENT_STRING_RANGE )
    @FormParam ( "comment" )
    public String comment;

    public Comments set_comment_data(Users user, Posts post)
    {

        Comments comment = new Comments();

        comment.setUser(user);
        comment.setPost(post);
        comment.setComment(this.comment);
        comment.setCreate_at(Calendar.getInstance().getTime());
        comment.setModified_at(Calendar.getInstance().getTime());

        return comment;
    }
}
