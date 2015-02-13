package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.model.posts;
import mini.model.users;
import mini.systemvalue.SystemValue;

public class PostCreateForm {
	@NotNull
	@Size(min=SystemValue.POST_TITLE_MIN_LENGTH,max = SystemValue.POST_TITLE_MAX_LENGTH)
	@FormParam("title")
	public String title;
	@NotNull
	@Size(min=SystemValue.POST_CONTENT_MIN_LENGTH,max = SystemValue.POST_CONTENT_MAX_LENGTH)
	@FormParam("content")
	public String content;
	
	public posts set_post_data(users user){
		posts post = new posts();

		post.setTitle(this.title);
		post.setContent(this.content);
		post.setUser(user);
		post.setStatus(true);
		post.setCreate_at(Calendar.getInstance().getTime());
		post.setModified_at(Calendar.getInstance().getTime());
		
		return post;
	}
}
