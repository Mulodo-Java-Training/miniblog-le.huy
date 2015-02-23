package mini.dao;

import mini.model.Token;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Le Dang Huy
 */
@Repository
public class TokenDAO implements TokenDAOInterface
{

    @Autowired
    private SessionFactory session;

    @Override
    public void save(Token token)
    {

        session.getCurrentSession().save(token);
    }

    // @Override
    // public void persist(Token token)
    // {
    //
    // session.getCurrentSession().persist(token);
    // }

    @Override
    public void update(Token token)
    {

        session.getCurrentSession().update(token);
    }

    // @Override
    // public void saveOrUpdate(Token token)
    // {
    //
    // session.getCurrentSession().saveOrUpdate(token);
    // }

    @Override
    public void delete(Token token)
    {

        session.getCurrentSession().delete(token);
    }

    // @Override
    // public Token get(int id)
    // {
    //
    // return (Token) session.getCurrentSession().get(Token.class, id);
    // }
    //
    // @Override
    // public Token load(int id)
    // {
    //
    // return (Token) session.getCurrentSession().load(Token.class, id);
    // }

    @Override
    public Token getTokenByAccessToken(String access_token)
    {

        return (Token) session.getCurrentSession()
                .createQuery("FROM Token token " + "WHERE token.access_token =:access_token")
                .setParameter("access_token", access_token).uniqueResult();

    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public List<token> getTokenByUserId(int user_id) {
    // try{
    // return session.getCurrentSession()
    // .createQuery("select token from token as token where token.user_id=:user_id")
    // .setParameter("user_id",user_id).list();
    // }catch(Exception e){
    // e.printStackTrace();
    // return null;
    // }
    // }

    @Override
    public void clearTokenByUserId(int user_id)
    {

        session.getCurrentSession().createQuery("DELETE FROM Token " + "WHERE user_id=:user_id")
                .setParameter("user_id", user_id).executeUpdate();
    }

}
