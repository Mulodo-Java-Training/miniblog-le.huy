package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.model.comments;
import mini.model.posts;
import mini.model.users;
import mini.systemvalue.SystemValue;

public class CommentCreateForm {
	@FormParam("postid")
	public int postid;
	@NotNull
	@Size(min = SystemValue.COMMENT_MIN_LENGTH, max = SystemValue.COMMENT_MAX_LENGTH)
	@FormParam("comment")
	public String comment;
	
	public comments set_comment_data(users user,posts post){
		comments comment = new comments();
		
		comment.setUser(user);
		comment.setPost(post);
		comment.setComment(this.comment);
		comment.setCreate_at(Calendar.getInstance().getTime());
		comment.setModified_at(Calendar.getInstance().getTime());
		
		return comment;
	}
}
