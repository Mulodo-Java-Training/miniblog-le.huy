package mini.dao;

import java.util.List;

import mini.model.Comments;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Le Dang Huy
 *
 */

@Repository
public class CommentDAO implements CommentDAOInterface
{

    @Autowired
    private SessionFactory session;

    @Override
    public int save(Comments comment)
    {

        return (int) session.getCurrentSession().save(comment);
    }

    // @Override
    // public void persist(Comments comment)
    // {
    //
    // session.getCurrentSession().persist(comment);
    // }

    @Override
    public void update(Comments comment)
    {

        session.getCurrentSession().update(comment);
    }

    // @Override
    // public void saveOrUpdate(Comments comment)
    // {
    //
    // session.getCurrentSession().saveOrUpdate(comment);
    // }

    @Override
    public void delete(Comments comment)
    {

        session.getCurrentSession().delete(comment);
    }

    @Override
    public Comments get(int id)
    {

        return (Comments) session.getCurrentSession().get(Comments.class, id);
    }

    // @Override
    // public Comments load(int id)
    // {
    //
    // return (Comments) session.getCurrentSession().load(Comments.class, id);
    // }

    @SuppressWarnings ( "unchecked" )
    @Override
    public List<Comments> getAllCommentsByUserId(int user_id)
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "FROM Comments comment "
                                + "WHERE comment.user.id=:user_id")
                .setParameter("user_id", user_id).list();
    }

    @SuppressWarnings ( "unchecked" )
    @Override
    public List<Comments> getAllCommentsByPostId(int post_id)
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "FROM Comments comment "
                                + "WHERE comment.post.id=:post_id")
                .setParameter("post_id", post_id).list();
    }

}
