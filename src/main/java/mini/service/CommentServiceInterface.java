package mini.service;

import java.util.List;

import mini.form.request.CommentEditForm;
import mini.model.Comments;

/**
 * @author Le Dang Huy
 */
public interface CommentServiceInterface
{

    public int createComment(Comments data);

    public boolean checkCommentOwn(int comment_id, int user_id);

    public boolean editComment(CommentEditForm data);

    public boolean deleteComment(CommentEditForm data);

    public Comments getCommentById(int comment_id);

    @SuppressWarnings("rawtypes")
    public List getAllCommentByUserId(int user_id);

    @SuppressWarnings("rawtypes")
    public List getAllCommentByPostId(int post_id);

}
