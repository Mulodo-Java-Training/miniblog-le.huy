package mini.service;

import java.util.List;

import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;

/**
 * @author Le Dang Huy
 *
 */

public interface UserServiceInterface
{

    public boolean checkUserExist(String username);

    public boolean checkEmailExist(String email);

    public Users getUserByUsername(String username);

    public boolean deleteUser(String username);
    
    public boolean insertUser(Users user);

    public Token loginUser(UserLoginForm data);

    public boolean logoutUser(Token token);

    public boolean updateUserInfo(UserUpdateForm data, int user_id);

    public Users getUserById(int id);

    public Token changePassword(UserChangePasswordForm data, int user_id);

    @SuppressWarnings ( "rawtypes" )
    public List searchUserByUsername(String query);
}
