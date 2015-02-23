package mini.form.request;

import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 */
public class PostEditForm
{

    @FormParam("id")
    public int id;

    @Pattern(regexp = SystemValue.POST_TITLE_STRING_RANGE)
    @FormParam("title")
    public String title;

    @Pattern(regexp = SystemValue.POST_CONTENT_STRING_RANGE)
    @FormParam("content")
    public String content;

    @FormParam("status")
    public boolean status;

}
