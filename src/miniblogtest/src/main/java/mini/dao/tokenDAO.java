package mini.dao;

import java.util.List;

import mini.model.token;
import mini.model.users;

public interface tokenDAO {
	public void save(token token);
	public void persist(token token);
	public void update(token token);
	public void saveorupdate(token token);
	public void delete(token token);
	
	public users get(int id);
	public users load(int id);
	//custom function
	public token get_token_by_access_token(String access_token);
	public List<token> search_token_by_userId(int user_id);
}
