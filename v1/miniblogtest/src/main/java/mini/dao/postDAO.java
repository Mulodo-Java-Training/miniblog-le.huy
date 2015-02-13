package mini.dao;

import java.util.List;

import mini.form.response.PostShort;
import mini.model.posts;
import mini.systemvalue.TableCollumName;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAO implements PostDAOInterface {

	@Autowired
	private SessionFactory session;

	@Override
	public int save(posts post) {
		return (int) session.getCurrentSession().save(post);
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
	public void saveOrUpdate(posts post) {
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
	public List<PostShort> getAllPostsByUserId(int user_id) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(posts.class,"ps")
				.add(Restrictions.eq("ps"+TableCollumName.POSTS_USER_ID_FOREIGN, user_id))
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.POSTS_ID).as(TableCollumName.POSTS_ID))
				.add(Projections.property(TableCollumName.POSTS_TITLE).as(TableCollumName.POSTS_TITLE))
				.add(Projections.property(TableCollumName.POSTS_STATUS).as(TableCollumName.POSTS_STATUS))
				.add(Projections.property("ps"+TableCollumName.POSTS_USER_ID_FOREIGN).as(TableCollumName.POSTS_USER_ID))
				.add(Projections.property(TableCollumName.POSTS_CREATE_AT).as(TableCollumName.POSTS_CREATE_AT))
				.add(Projections.property(TableCollumName.POSTS_MODIFIED_AT).as(TableCollumName.POSTS_MODIFIED_AT)))
				.setResultTransformer(new AliasToBeanResultTransformer(PostShort.class));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostShort> getAllPosts() {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(posts.class,"ps")
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.POSTS_ID).as(TableCollumName.POSTS_ID))
				.add(Projections.property(TableCollumName.POSTS_TITLE).as(TableCollumName.POSTS_TITLE))
				.add(Projections.property(TableCollumName.POSTS_STATUS).as(TableCollumName.POSTS_STATUS))
				.add(Projections.property("ps"+TableCollumName.POSTS_USER_ID_FOREIGN).as(TableCollumName.POSTS_USER_ID))
				.add(Projections.property(TableCollumName.POSTS_CREATE_AT).as(TableCollumName.POSTS_CREATE_AT))
				.add(Projections.property(TableCollumName.POSTS_MODIFIED_AT).as(TableCollumName.POSTS_MODIFIED_AT)))
				.setResultTransformer(new AliasToBeanResultTransformer(PostShort.class));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostShort> searchPostsByCreateAt(int limit) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(posts.class,"ps")
				.addOrder(Order.desc(TableCollumName.POSTS_CREATE_AT))
				.setMaxResults(limit)
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.POSTS_ID).as(TableCollumName.POSTS_ID))
				.add(Projections.property(TableCollumName.POSTS_TITLE).as(TableCollumName.POSTS_TITLE))
				.add(Projections.property(TableCollumName.POSTS_STATUS).as(TableCollumName.POSTS_STATUS))
				.add(Projections.property("ps"+TableCollumName.POSTS_USER_ID_FOREIGN).as(TableCollumName.POSTS_USER_ID))
				.add(Projections.property(TableCollumName.POSTS_CREATE_AT).as(TableCollumName.POSTS_CREATE_AT))
				.add(Projections.property(TableCollumName.POSTS_MODIFIED_AT).as(TableCollumName.POSTS_MODIFIED_AT)))
				.setResultTransformer(new AliasToBeanResultTransformer(PostShort.class));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostShort> searchPostsByTitle(String keysearch) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(posts.class,"ps")
				.add(Restrictions.like(TableCollumName.POSTS_TITLE, keysearch,MatchMode.ANYWHERE))
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.POSTS_ID).as(TableCollumName.POSTS_ID))
				.add(Projections.property(TableCollumName.POSTS_TITLE).as(TableCollumName.POSTS_TITLE))
				.add(Projections.property(TableCollumName.POSTS_STATUS).as(TableCollumName.POSTS_STATUS))
				.add(Projections.property("ps"+TableCollumName.POSTS_USER_ID_FOREIGN).as(TableCollumName.POSTS_USER_ID))
				.add(Projections.property(TableCollumName.POSTS_CREATE_AT).as(TableCollumName.POSTS_CREATE_AT))
				.add(Projections.property(TableCollumName.POSTS_MODIFIED_AT).as(TableCollumName.POSTS_MODIFIED_AT)))
				.setResultTransformer(new AliasToBeanResultTransformer(PostShort.class));
		return criteria.list();
	}
}
