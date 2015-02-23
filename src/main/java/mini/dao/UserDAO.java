package mini.dao;

import java.util.List;

import mini.model.Users;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Le Dang Huy
 *
 */

@Repository
public class UserDAO implements UserDAOInterface
{

    @Autowired
    private SessionFactory session;

    @Override
    public void save(Users user)
    {

        session.getCurrentSession().save(user);
    }

    // @Override
    // public void persist(Users user)
    // {
    //
    // session.getCurrentSession().persist(user);
    // }

    @Override
    public void update(Users user)
    {

        session.getCurrentSession().update(user);
    }

    // @Override
    // public void saveOrUpdate(Users user)
    // {
    //
    // session.getCurrentSession().saveOrUpdate(user);
    // }

    @Override
    public void delete(Users user)
    {

        session.getCurrentSession().delete(user);
    }
    
    @Override
    public void delete(String username)
    {
        session.getCurrentSession()
        .createQuery("DELETE FROM Users " + "WHERE username=:username")
        .setParameter("username", username).executeUpdate();
    }

    @Override
    public Users get(int id)
    {

        return (Users) session.getCurrentSession().get(Users.class, id);
    }

    // @Override
    // public Users load(int id)
    // {
    //
    // return (Users) session.getCurrentSession().load(Users.class, id);
    // }

    // @SuppressWarnings ( "rawtypes" )
    // @Override
    // public List getAllUser()
    // {
    //
    // return session
    // .getCurrentSession()
    // .createQuery(
    // "SELECT user.id as id,"
    // + "user.username as username,"
    // + "user.email as email "
    // + "FROM Users user ")
    // .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    //
    // }

    @Override
    public Users getUserByUsername(String username)
    {

        Criteria criteria = session.getCurrentSession().createCriteria(
                Users.class);
        criteria.add(Restrictions.eq("username", username));
        return (Users) criteria.uniqueResult();
    }

    @Override
    public Users getUserByEmail(String email)
    {

        Criteria criteria = session.getCurrentSession().createCriteria(
                Users.class);
        criteria.add(Restrictions.eq("email", email));
        return (Users) criteria.uniqueResult();
    }

    @SuppressWarnings ( "rawtypes" )
    @Override
    public List searchUserByUsername(String query)
    {

        return session
                .getCurrentSession()

                .createQuery(
                        "SELECT user.id as id," + "user.username as username,"
                                + "user.email as email " + "FROM Users user "
                                + "WHERE user.username LIKE :query")
                .setParameter("query", "%" + query + "%")
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

    }

}
