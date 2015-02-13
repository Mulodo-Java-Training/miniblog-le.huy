package mini.dao;

import java.util.List;

import mini.form.response.PostShort;
import mini.model.posts;

public interface PostDAOInterface {
    public int save(posts post);
    public void persist(posts post);
    public void update(posts post);
    public void saveOrUpdate(posts post);
    public void delete(posts post);

    public posts get(int id);
    public posts load(int id);

    //custom function
    public List<PostShort> getAllPosts();
    public List<PostShort> getAllPostsByUserId(int user_id);

    public List<PostShort> searchPostsByCreateAt(int limit);
    public List<PostShort> searchPostsByTitle(String keysearch);

    /* not yet
    */
}
