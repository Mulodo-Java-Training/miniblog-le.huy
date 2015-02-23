package unitTest.testResource.Controller;

import java.util.List;

import org.springframework.stereotype.Service;

import mini.form.request.PostEditForm;
import mini.model.Posts;
import mini.service.PostServiceInterface;

@Service
public class PostServiceTest implements PostServiceInterface
{

    @Override
    public int createPost(Posts data)
    {

        if (data.getTitle() == "createpost3001") {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean checkPostOwn(int post_id, int user_id)
    {

        if (post_id == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean editPost(PostEditForm data)
    {

        if (data.id == 2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean editStatusPost(PostEditForm data)
    {

        if (data.id == 2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean deletePost(PostEditForm data)
    {

        if (data.id == 2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List getAllPost()
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getAllPostByUserId(int user_id)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Posts getPostByPostId(int post_id)
    {

        if (post_id == 1) {
            return new Posts();
        } else {
            return null;
        }
    }

    @Override
    public List getTopPost(int limit)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List searchPostByTitle(String keysearch)
    {

        // TODO Auto-generated method stub
        return null;
    }

}
