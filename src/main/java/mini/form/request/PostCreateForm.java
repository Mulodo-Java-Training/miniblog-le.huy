package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.model.Posts;
import mini.model.Users;
import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 */
public class PostCreateForm
{

    @NotNull
    @Pattern(regexp = SystemValue.POST_TITLE_STRING_RANGE)
    @FormParam("title")
    public String title;

    @NotNull
    @Pattern(regexp = SystemValue.POST_CONTENT_STRING_RANGE)
    @FormParam("content")
    public String content;

    public Posts set_post_data(Users user)
    {

        Posts post = new Posts();

        post.setTitle(this.title);
        post.setContent(this.content);
        post.setUser(user);
        post.setStatus(true);
        post.setCreate_at(Calendar.getInstance().getTime());
        post.setModified_at(Calendar.getInstance().getTime());

        return post;
    }
}
