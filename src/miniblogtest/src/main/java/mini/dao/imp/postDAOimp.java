package mini.dao.imp;

import java.util.List;

import mini.dao.postDAO;
import mini.model.posts;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class postDAOimp implements postDAO{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}

	@Override
	public void save(posts post) {
		session.getCurrentSession().save(post);
	}

	@Override
	public void persist(posts post) {
		session.getCurrentSession().persist(post);
	}

	@Override
	public void update(posts post) {
		session.getCurrentSession().update(post);
	}

	@Override
	public void saveorupdate(posts post) {
		session.getCurrentSession().saveOrUpdate(post);
	}

	@Override
	public void delete(posts post) {
		session.getCurrentSession().delete(post);
	}

	@Override
	public posts get(int id) {
		return (posts) session.getCurrentSession().get(posts.class, id);
	}

	@Override
	public posts load(int id) {
		return (posts) session.getCurrentSession().load(posts.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<posts> get_all_posts_by_user_Id(int user_id) {
		Criteria criteria = session.getCurrentSession().createCriteria(posts.class);
		criteria.add(Restrictions.eq("user_id", user_id));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<posts> get_all_posts() {
		return session.getCurrentSession().createQuery("from posts").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<posts> search_posts_by_create_at(int limit) {
		return null;
//		Criteria criteria = session.getCurrentSession().createCriteria(posts.class);
//		criteria.add(Restrictions.eq("user_id", user_id));
//		criteria.setProjection(Projections.max("InvoiceDate" ))
//		return criteria.list();
	}
}
