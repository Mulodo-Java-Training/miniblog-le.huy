package unitTest.testResource.Controller;

import java.util.List;

import org.springframework.stereotype.Service;

import mini.form.request.CommentEditForm;
import mini.model.Comments;
import mini.service.CommentServiceInterface;

@Service
public class CommentServiceTest implements CommentServiceInterface
{

    @Override
    public int createComment(Comments data)
    {

        if (data.getComment() == "comment200") {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean checkCommentOwn(int comment_id, int user_id)
    {

        if (comment_id == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean editComment(CommentEditForm data)
    {

        if (data.id == 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteComment(CommentEditForm data)
    {

        if (data.id == 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Comments getCommentById(int comment_id)
    {

        Comments comment = new Comments();
        if (comment_id == 1) {
            return comment;
        } else {
            return null;
        }
    }

    @Override
    public List getAllCommentByUserId(int user_id)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getAllCommentByPostId(int post_id)
    {

        // TODO Auto-generated method stub
        return null;
    }

}
