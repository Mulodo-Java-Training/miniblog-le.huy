package mini.form.request;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

public class PostEditForm {
	@FormParam("id")
	public int id;
	@Size(min=SystemValue.POST_TITLE_MIN_LENGTH,max = SystemValue.POST_TITLE_MAX_LENGTH)
	@FormParam("title")
	public String title;
	@Size(min=SystemValue.POST_CONTENT_MIN_LENGTH,max = SystemValue.POST_CONTENT_MAX_LENGTH)
	@FormParam("content")
	public String content;
	@FormParam("status")
	public boolean status;
}
