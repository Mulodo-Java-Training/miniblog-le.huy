package mini.form.response;

import java.util.Date;

import mini.model.comments;

public class CommentFull {
	public int id;
	public String comment;
	public Date create_at;
	public Date modified_at;
	public int userid;
	public int postid;

	public CommentFull(comments comment) {
		super();
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.create_at = comment.getCreate_at();
		this.modified_at = comment.getModified_at();
		this.userid = comment.getUser().getId();
		this.postid = comment.getPost().getId();
	}

	public CommentFull() {}
}
