package mini.dao;

import java.util.List;

import mini.model.posts;

public interface postDAO {
	public void save(posts post);
	public void persist(posts post);
	public void update(posts post);
	public void saveorupdate(posts post);
	public void delete(posts post);
	
	public posts get(int id);
	public posts load(int id);
	//custom function
	public List<posts> get_all_posts();
	public List<posts> get_all_posts_by_user_Id(int user_id);

	public List<posts> search_posts_by_create_at(int limit);
	/* not yet
	*/
}
