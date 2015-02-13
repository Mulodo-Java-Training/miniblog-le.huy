package mini.dao;

import java.util.List;

import mini.form.response.CommentFull;
import mini.model.comments;
import mini.systemvalue.TableCollumName;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CommentDAO implements CommentDAOInterface{

    @Autowired
    private SessionFactory session;

    @Override
    public int save(comments comment) {
        return (int) session.getCurrentSession().save(comment);
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
    public void saveOrUpdate(comments comment) {
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
    public List<CommentFull> getAllCommentsByUserId(int user_id) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(comments.class,"com")
				.add(Restrictions.eq("com"+TableCollumName.COMMENTS_USER_ID_FOREIGN, user_id))
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.COMMENTS_ID).as(TableCollumName.COMMENTS_ID))
				.add(Projections.property(TableCollumName.COMMENTS_COMMENT).as(TableCollumName.COMMENTS_COMMENT))
				.add(Projections.property(TableCollumName.COMMENTS_CREATE_AT).as(TableCollumName.COMMENTS_CREATE_AT))
				.add(Projections.property(TableCollumName.COMMENTS_MODIFIED_AT).as(TableCollumName.COMMENTS_MODIFIED_AT))
				.add(Projections.property("com"+TableCollumName.COMMENTS_USER_ID_FOREIGN).as(TableCollumName.COMMENTS_USER_ID))
				.add(Projections.property("com"+TableCollumName.COMMENTS_POST_ID_FOREIGN).as(TableCollumName.COMMENTS_POST_ID)))
				.setResultTransformer(new AliasToBeanResultTransformer(CommentFull.class));
		return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CommentFull> getAllCommentsByPostId(int post_id) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(comments.class,"com")
				.add(Restrictions.eq("com"+TableCollumName.COMMENTS_POST_ID, post_id))
				.setProjection(Projections.projectionList()
				.add(Projections.property(TableCollumName.COMMENTS_ID).as(TableCollumName.COMMENTS_ID))
				.add(Projections.property(TableCollumName.COMMENTS_COMMENT).as(TableCollumName.COMMENTS_COMMENT))
				.add(Projections.property(TableCollumName.COMMENTS_CREATE_AT).as(TableCollumName.COMMENTS_CREATE_AT))
				.add(Projections.property(TableCollumName.COMMENTS_MODIFIED_AT).as(TableCollumName.COMMENTS_MODIFIED_AT))
				.add(Projections.property("com"+TableCollumName.COMMENTS_USER_ID_FOREIGN).as(TableCollumName.COMMENTS_USER_ID))
				.add(Projections.property("com"+TableCollumName.COMMENTS_POST_ID_FOREIGN).as(TableCollumName.COMMENTS_POST_ID)))
				.setResultTransformer(new AliasToBeanResultTransformer(CommentFull.class));
		return criteria.list();
    }

}
