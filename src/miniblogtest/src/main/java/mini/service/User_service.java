package mini.service;

import mini.model.users;

public interface User_service {
	boolean Validate_user(users user);
	boolean Check_User_Exist(String username);
	boolean Insert_user(users user);
	boolean Create_token();
}
