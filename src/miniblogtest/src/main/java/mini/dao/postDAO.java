package mini.dao;

import java.util.List;

import mini.model.posts;
import mini.model.users;

public interface postDAO {
	public void save(posts post);
	public void persist(posts post);
	public void update(posts post);
	public void saveorupdate(posts post);
	public void delete(posts post);
	
	public users get(int id);
	public users load(int id);
	//custom function
	public List<posts> get_all_posts_by_user_Id(int user_id);
	/* not yet
	public List<users> search_posts_by_title(String title);
	*/
}
