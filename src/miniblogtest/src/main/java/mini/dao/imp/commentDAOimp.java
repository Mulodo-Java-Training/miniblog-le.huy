package mini.dao.imp;

import java.util.List;

import mini.dao.commentDAO;
import mini.model.comments;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class commentDAOimp implements commentDAO{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}

	@Override
	public void save(comments comment) {
		session.getCurrentSession().save(comment);
	}

	@Override
	public void persist(comments comment) {
		session.getCurrentSession().persist(comment);
	}

	@Override
	public void update(comments comment) {
		session.getCurrentSession().update(comment);
	}

	@Override
	public void saveorupdate(comments comment) {
		session.getCurrentSession().saveOrUpdate(comment);
	}

	@Override
	public void delete(comments comment) {
		session.getCurrentSession().delete(comment);
	}

	@Override
	public comments get(int id) {
		return (comments) session.getCurrentSession().get(comments.class,id);
	}

	@Override
	public comments load(int id) {
		return (comments) session.getCurrentSession().load(comments.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<comments> get_all_comments_by_userId(int user_id) {
		Criteria criteria = session.getCurrentSession().createCriteria(comments.class);
		criteria.add(Restrictions.eq("user_id", user_id));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<comments> get_all_comments_by_postId(int post_id) {
		Criteria criteria = session.getCurrentSession().createCriteria(comments.class);
		criteria.add(Restrictions.eq("post_id", post_id));
		return criteria.list();
	}

}
