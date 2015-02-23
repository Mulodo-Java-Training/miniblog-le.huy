package mini.dao;

import java.util.List;

import mini.model.Posts;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Le Dang Huy
 */
@Repository
public class PostDAO implements PostDAOInterface
{

    @Autowired
    private SessionFactory session;

    @Override
    public int save(Posts post)
    {

        return (int) session.getCurrentSession().save(post);
    }

    // @Override
    // public void persist(Posts post)
    // {
    //
    // session.getCurrentSession().persist(post);
    // }

    @Override
    public void update(Posts post)
    {

        session.getCurrentSession().update(post);
    }

    // @Override
    // public void saveOrUpdate(Posts post)
    // {
    //
    // session.getCurrentSession().saveOrUpdate(post);
    // }

    @Override
    public void delete(Posts post)
    {

        session.getCurrentSession().delete(post);
    }

    @Override
    public Posts get(int id)
    {

        return (Posts) session.getCurrentSession().get(Posts.class, id);
    }

    // @Override
    // public Posts load(int id)
    // {
    //
    // return (Posts) session.getCurrentSession().load(Posts.class, id);
    // }

    @SuppressWarnings("rawtypes")
    @Override
    public List getAllPostsByUserId(int user_id)
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "SELECT post.id as id,"
                                + "post.title as title,"
                                + "post.status as status,"
                                + "CONCAT(date(post.create_at),' ',time(post.create_at)) as create_at,"
                                + "CONCAT(date(post.modified_at),' ',time(post.modified_at)) as modified_at "
                                + "FROM Posts post " + "WHERE post.user.id =:user_id")
                .setParameter("user_id", user_id)
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getAllPosts()
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "SELECT post.id as id,"
                                + "post.title as title,"
                                + "post.status as status,"
                                + "CONCAT(date(post.create_at),' ',time(post.create_at)) as create_at,"
                                + "CONCAT(date(post.modified_at),' ',time(post.modified_at)) as modified_at "
                                + "FROM Posts post")
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List searchPostsByCreateAt(int limit)
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "SELECT post.id as id,"
                                + "post.title as title,"
                                + "post.status as status,"
                                + "CONCAT(date(post.create_at),' ',time(post.create_at)) as create_at,"
                                + "CONCAT(date(post.modified_at),' ',time(post.modified_at)) as modified_at "
                                + "FROM Posts post " + "ORDER BY post.create_at DESC")
                .setMaxResults(limit).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List searchPostsByTitle(String keysearch)
    {

        return session
                .getCurrentSession()
                .createQuery(
                        "SELECT "
                                + "post.id as id,"
                                + "post.title as title,"
                                + "post.status as status,"
                                + "CONCAT(date(post.create_at),' ',time(post.create_at)) as create_at,"
                                + "CONCAT(date(post.modified_at),' ',time(post.modified_at)) as modified_at "
                                + "FROM Posts post " + "WHERE post.title LIKE :keysearch")
                .setParameter("keysearch", "%" + keysearch + "%")
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
}
