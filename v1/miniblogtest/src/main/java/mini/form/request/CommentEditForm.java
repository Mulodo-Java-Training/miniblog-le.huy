package mini.form.request;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

public class CommentEditForm {
	@FormParam("id")
	public int id;
	@FormParam("postid")
	public int postid;
	@Size(min = SystemValue.COMMENT_MIN_LENGTH, max = SystemValue.COMMENT_MAX_LENGTH)
	@FormParam("comment")
	public String comment;
}
