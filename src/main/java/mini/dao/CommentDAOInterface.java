package mini.dao;

import java.util.List;

import mini.model.Comments;

/**
 * @author Le Dang Huy
 *
 */

public interface CommentDAOInterface
{

    public int save(Comments comment);

    // public void persist(Comments comment);

    public void update(Comments comment);

    // public void saveOrUpdate(Comments comment);

    public void delete(Comments comment);

    public Comments get(int id);

    // public Comments load(int id);

    // custom function
    public List<Comments> getAllCommentsByUserId(int user_id);

    public List<Comments> getAllCommentsByPostId(int post_id);

    /*
     * not yet public List<comments> search_comments_by_comment(String
     * comment_content);
     */
}
