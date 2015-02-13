package mini.dao;

import java.util.List;

import mini.form.response.UserShort;
import mini.model.users;

public interface UserDAOInterface {
    public void save(users user);
    public void persist(users user);
    public void update(users user);
    public void saveOrUpdate(users user);
    public void delete(users user);

    public users get(int id);
    public users load(int id);

    //custom function
    public List<users> getAllUser();
    public users getUserByUsername(String username);
    public users getUserByEmail(String email);

    public List<UserShort> searchUserByUsername(String query);

    /* not yet
    public List<users> search_user_by_firstname(String firstname);
    public List<users> search_user_by_lastname(String lastname);
    */
}
