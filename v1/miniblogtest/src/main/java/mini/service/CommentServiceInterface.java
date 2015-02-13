package mini.service;

import java.util.List;

import mini.form.request.CommentEditForm;
import mini.form.response.CommentFull;
import mini.model.comments;

public interface CommentServiceInterface {
	public int createComment(comments data);
	public boolean checkCommentOwn(int comment_id,int user_id);
	public boolean editComment(CommentEditForm data);
	public boolean deleteComment(CommentEditForm data);
	
	public comments getCommentById(int comment_id);
	public List<CommentFull> getAllCommentByUserId(int user_id);
	public List<CommentFull> getAllCommentByPostId(int post_id);
	
}
