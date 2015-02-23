package mini.dao;

import java.util.List;

import mini.model.Posts;

/**
 * @author Le Dang Huy
 */
public interface PostDAOInterface
{

    public int save(Posts post);

    // public void persist(Posts post);

    public void update(Posts post);

    // public void saveOrUpdate(Posts post);

    public void delete(Posts post);

    public Posts get(int id);

    // public Posts load(int id);

    // custom function
    @SuppressWarnings("rawtypes")
    public List getAllPosts();

    @SuppressWarnings("rawtypes")
    public List getAllPostsByUserId(int user_id);

    @SuppressWarnings("rawtypes")
    public List searchPostsByCreateAt(int limit);

    @SuppressWarnings("rawtypes")
    public List searchPostsByTitle(String keysearch);

    /*
     * not yet
     */
}
