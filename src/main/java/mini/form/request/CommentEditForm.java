package mini.form.request;

import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 *
 */

public class CommentEditForm
{

    @FormParam ( "id" )
    public int id;

    @FormParam ( "postid" )
    public int postid;

    @Pattern ( regexp = SystemValue.COMMENT_STRING_RANGE )
    @FormParam ( "comment" )
    public String comment;

}
