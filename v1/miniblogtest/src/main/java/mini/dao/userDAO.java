package mini.dao;

import java.util.List;

import mini.form.response.UserShort;
import mini.model.users;
import mini.systemvalue.TableCollumName;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements UserDAOInterface{

    @Autowired
    private SessionFactory session;

    @Override
    public void save(users user) {
        session.getCurrentSession().save(user);
    }

    @Override
    public void persist(users user) {
        session.getCurrentSession().persist(user);
    }

    @Override
    public void update(users user) {
        session.getCurrentSession().update(user);
    }

    @Override
    public void saveOrUpdate(users user) {
        session.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(users user) {
        session.getCurrentSession().delete(user);
    }

    @Override
    public users get(int id) {
        return (users) session.getCurrentSession().get(users.class, id);
    }

    @Override
    public users load(int id) {
        return (users) session.getCurrentSession().load(users.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<users> getAllUser() {
        return session.getCurrentSession().createQuery("from "+users.class.getName()).list();
    }

    @Override
    public users getUserByUsername(String username) {
        Criteria criteria = session.getCurrentSession().createCriteria(users.class);
        criteria.add(Restrictions.eq(TableCollumName.USERS_USERNAME, username));
        return (users) criteria.uniqueResult();
    }

    @Override
    public users getUserByEmail(String email) {
        Criteria criteria = session.getCurrentSession().createCriteria(users.class);
        criteria.add(Restrictions.eq(TableCollumName.USERS_EMAIL, email));
        return (users) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserShort> searchUserByUsername(String query) {
        Criteria criteria = session.getCurrentSession().createCriteria(users.class)
        			.add(Restrictions.like(TableCollumName.USERS_USERNAME, query,MatchMode.ANYWHERE))
        			.setProjection( Projections.projectionList()
    		        .add( Projections.property(TableCollumName.USERS_ID).as(TableCollumName.USERS_ID))
    		        .add( Projections.property(TableCollumName.USERS_USERNAME).as(TableCollumName.USERS_USERNAME))
    		        .add( Projections.property(TableCollumName.USERS_EMAIL).as(TableCollumName.USERS_EMAIL)))
    		        .setResultTransformer(new AliasToBeanResultTransformer(UserShort.class));
		return criteria.list();
	}

}
