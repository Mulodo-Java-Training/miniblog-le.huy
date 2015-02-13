package mini.dao;

import java.util.List;

import mini.form.response.CommentFull;
import mini.model.comments;

public interface CommentDAOInterface {
    public int save(comments comment);
    public void persist(comments comment);
    public void update(comments comment);
    public void saveOrUpdate(comments comment);
    public void delete(comments comment);

    public comments get(int id);
    public comments load(int id);

    //custom function
    public List<CommentFull> getAllCommentsByUserId(int user_id);
    public List<CommentFull> getAllCommentsByPostId(int post_id);

    /* not yet
    public List<comments> search_comments_by_comment(String comment_content);
    */
}
