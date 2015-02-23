package mini.service;

import java.util.List;

import mini.form.request.PostEditForm;
import mini.model.Posts;

/**
 * @author Le Dang Huy
 *
 */

public interface PostServiceInterface
{

    public int createPost(Posts data);

    public boolean checkPostOwn(int post_id, int user_id);

    public boolean editPost(PostEditForm data);

    public boolean editStatusPost(PostEditForm data);

    public boolean deletePost(PostEditForm data);

    @SuppressWarnings ( "rawtypes" )
    public List getAllPost();

    @SuppressWarnings ( "rawtypes" )
    public List getAllPostByUserId(int user_id);

    public Posts getPostByPostId(int post_id);

    @SuppressWarnings ( "rawtypes" )
    public List getTopPost(int limit);

    @SuppressWarnings ( "rawtypes" )
    public List searchPostByTitle(String keysearch);
}
