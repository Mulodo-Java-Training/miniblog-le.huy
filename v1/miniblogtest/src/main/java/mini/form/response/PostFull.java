package mini.form.response;

import java.util.Date;

import mini.model.posts;

public class PostFull {
	public int id;
	public String title;
	public String content;
	public boolean status;
	public int user_id;
	public Date create_at;
	public Date modified_at;

	public PostFull(posts post) {
		super();
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.status = post.isStatus();
		this.user_id = post.getUser().getId();
		this.create_at = post.getCreate_at();
		this.modified_at = post.getModified_at();
	}
}
