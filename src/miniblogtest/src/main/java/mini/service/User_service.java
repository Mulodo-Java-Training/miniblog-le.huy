package mini.service;

import mini.model.users;
import mini.resteasy.form.signupform;

public interface User_service {
	public boolean Validate_user(signupform user);
	public boolean Check_User_Exist(String username);
	public boolean Check_Email_Exist(String email);
	public users Insert_user(users user);
	public users get_user_by_username(String username);
	
	public boolean Create_token();
}
