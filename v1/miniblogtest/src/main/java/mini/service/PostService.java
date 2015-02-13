package mini.service;

import java.util.Calendar;
import java.util.List;

import mini.dao.PostDAOInterface;
import mini.dao.UserDAOInterface;
import mini.form.request.PostEditForm;
import mini.form.response.PostShort;
import mini.model.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService implements PostServiceInterface{
	
	@Autowired
	private PostDAOInterface post_DAO;
	@Autowired
	private UserDAOInterface user_DAO;
	
	@Override
	@Transactional
	public int createPost(posts data) {
		try{
			return post_DAO.save(data);
		}catch(Exception e){return 0;}
	}

	@Override
	@Transactional
	public boolean checkPostOwn(int post_id,int user_id) {
		try{
			posts post = post_DAO.get(post_id);
			if(post.getUser().getId()!=user_id){
				return false;}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Override
	@Transactional
	public boolean editPost(PostEditForm data){
		try{
			posts post = post_DAO.get(data.id);
			post.setTitle(data.title);
			post.setContent(data.content);
			post.setModified_at(Calendar.getInstance().getTime());
			post_DAO.update(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Override
	@Transactional
	public boolean editStatusPost(PostEditForm data) {
		try{
			posts post = post_DAO.get(data.id);
			post.setStatus(data.status);
			post.setModified_at(Calendar.getInstance().getTime());
			post_DAO.update(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Override
	@Transactional
	public boolean deletePost(PostEditForm data) {
		try{
			posts post = post_DAO.get(data.id);
			post_DAO.delete(post);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;}
	}

	@Override
	@Transactional
	public List<PostShort> getAllPost() {
		try{
			return post_DAO.getAllPosts();
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Override
	@Transactional
	public List<PostShort> getAllPostByUserId(int user_id) {
		try{
			return post_DAO.getAllPostsByUserId(user_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Override
	@Transactional
	public posts getPostByPostId(int post_id) {
		try{
			return post_DAO.get(post_id);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Override
	@Transactional
	public List<PostShort> getTopPost(int limit) {
		try{
			return post_DAO.searchPostsByCreateAt(limit);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}

	@Override
	@Transactional
	public List<PostShort> searchPostByTitle(String keysearch) {
		try{
			return post_DAO.searchPostsByTitle(keysearch);
		}catch(Exception e){
			e.printStackTrace();
			return null;}
	}
}
