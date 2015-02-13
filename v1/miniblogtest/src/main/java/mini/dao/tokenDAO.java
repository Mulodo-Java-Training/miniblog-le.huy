package mini.dao;

import mini.model.token;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class TokenDAO implements TokenDAOInterface {

    @Autowired
    private SessionFactory session;

    @Override
    public void save(token token) {
        session.getCurrentSession().save(token);
    }

    @Override
    public void persist(token token) {
        session.getCurrentSession().persist(token);
    }

    @Override
    public void update(token token) {
        session.getCurrentSession().update(token);
    }

    @Override
    public void saveOrUpdate(token token) {
        session.getCurrentSession().saveOrUpdate(token);
    }

    @Override
    public void delete(token token) {
        session.getCurrentSession().delete(token);
    }

    @Override
    public token get(int id) {
        return (token) session.getCurrentSession().get(token.class, id);
    }

    @Override
    public token load(int id) {
        return (token) session.getCurrentSession().load(token.class, id);
    }

    @Override
    public token getTokenByAccessToken(String access_token) {
        Criteria criteria = session.getCurrentSession().createCriteria(token.class);
        criteria.add(Restrictions.eq("access_token", access_token));
        return (token) criteria.uniqueResult();
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public List<token> getTokenByUserId(int user_id) {
//        try{
//            return session.getCurrentSession()
//            .createQuery("select token from token as token where token.user_id=:user_id")
//            .setParameter("user_id",user_id).list();
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public void clearTokenByUserId(int user_id) {
            session.getCurrentSession()
            .createQuery("delete from "+ token.class.getName()+" where user_id=:user_id")
            .setParameter("user_id",user_id)
            .executeUpdate();
    }

}
