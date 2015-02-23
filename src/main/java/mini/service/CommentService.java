package mini.service;

import java.util.Calendar;
import java.util.List;

import mini.dao.CommentDAOInterface;
import mini.form.request.CommentEditForm;
import mini.model.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Le Dang Huy
 *
 */

@Service
public class CommentService implements CommentServiceInterface
{

    @Autowired
    private CommentDAOInterface comment_DAO;

    @Override
    @Transactional
    public int createComment(Comments data)
    {

        try
        {
            return comment_DAO.save(data);
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Transactional
    public boolean checkCommentOwn(int comment_id, int user_id)
    {

        try
        {
            Comments comment = comment_DAO.get(comment_id);
            if (comment.getUser().getId() != user_id)
            {
                return false;
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean editComment(CommentEditForm data)
    {

        try
        {
            Comments comment = comment_DAO.get(data.id);
            comment.setComment(data.comment);
            comment.setModified_at(Calendar.getInstance().getTime());
            comment_DAO.update(comment);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteComment(CommentEditForm data)
    {

        try
        {
            Comments comment = comment_DAO.get(data.id);
            comment_DAO.delete(comment);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Comments getCommentById(int comment_id)
    {

        try
        {
            return comment_DAO.get(comment_id);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings ( "rawtypes" )
    @Override
    @Transactional
    public List getAllCommentByUserId(int user_id)
    {

        try
        {
            return comment_DAO.getAllCommentsByUserId(user_id);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings ( "rawtypes" )
    @Override
    @Transactional
    public List getAllCommentByPostId(int post_id)
    {

        try
        {
            return comment_DAO.getAllCommentsByPostId(post_id);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
