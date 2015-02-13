package mini.service;

import java.util.List;

import mini.form.request.PostEditForm;
import mini.form.response.PostShort;
import mini.model.posts;

public interface PostServiceInterface{
	public int createPost(posts data);
	public boolean checkPostOwn(int post_id,int user_id);
	public boolean editPost(PostEditForm data);
	public boolean editStatusPost(PostEditForm data);
	public boolean deletePost(PostEditForm data);
	
	public List<PostShort> getAllPost();
	public List<PostShort> getAllPostByUserId(int user_id);

	public posts getPostByPostId(int post_id);

	public List<PostShort> getTopPost(int limit);
	public List<PostShort> searchPostByTitle(String keysearch);
}
