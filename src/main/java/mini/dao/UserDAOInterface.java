package mini.dao;

import java.util.List;

import mini.model.Users;

/**
 * @author Le Dang Huy
 */
public interface UserDAOInterface
{

    public void save(Users user);

    // public void persist(Users user);

    public void update(Users user);

    // public void saveOrUpdate(Users user);

    public void delete(Users user);

    public void delete(String username);

    public Users get(int id);

    // public Users load(int id);

    // custom function
    // @SuppressWarnings ( "rawtypes" )
    // public List getAllUser();

    public Users getUserByUsername(String username);

    public Users getUserByEmail(String email);

    @SuppressWarnings("rawtypes")
    public List searchUserByUsername(String query);

    /*
     * not yet public List<users> search_user_by_firstname(String firstname);
     * public List<users> search_user_by_lastname(String lastname);
     */
}
