package mini.service;

import java.util.List;

import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserUpdateForm;
import mini.form.response.UserShort;
import mini.model.token;
import mini.model.users;

public interface UserServiceInterface {

	public boolean checkUserExist(String username);
	public boolean checkEmailExist(String email);

	public users getUserByUsername(String username);
	public boolean deleteUser(String username);
	
	public boolean insertUser(users user);	
	public String loginUser(UserLoginForm data);
	public boolean logoutUser(token token);	
	public boolean updateUserInfo(UserUpdateForm data,int user_id);
	public users getUserById(int id);
	public String changePassword(UserChangePasswordForm data,int user_id);
	public List<UserShort> searchUserByUsername(String query);
}
