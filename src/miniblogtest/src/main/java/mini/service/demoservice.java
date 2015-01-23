package mini.service;

import java.util.List;

public interface demoservice {
	public List search_user_by_username(String username);
	public List search_user_by_firstname(String firstname);
	public List search_user_by_lastname(String lastname);
}
