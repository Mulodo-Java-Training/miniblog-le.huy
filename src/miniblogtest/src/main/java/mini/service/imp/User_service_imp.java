package mini.service.imp;

import mini.dao.userDAO;
import mini.model.users;
import mini.resteasy.form.signupform;
import mini.service.User_service;
import mini.service.mysql_validation_value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User_service_imp implements User_service{
	
	@Autowired
	private userDAO userdao;

	@Override
	public boolean Validate_user(signupform user) {
		if(
			user.getUsername() !=null && 
			user.getUsername().length() <= mysql_validation_value.users_username_MAX_LENGTH &&
			user.getUsername().length() >= mysql_validation_value.users_username_MIN_LENGTH && 
			user.getUsername().matches(mysql_validation_value.users_username_STRING_RANGE)&&
			
			user.getPassword() !=null &&
			user.getPassword().length() <= mysql_validation_value.users_password_MAX_LENGTH &&
			user.getPassword().length() >= mysql_validation_value.users_password_MIN_LENGTH &&
			user.getPassword().matches(mysql_validation_value.users_password_STRING_RANGE) &&
			
			user.getFirstname() !=null &&
			user.getFirstname().length() <= mysql_validation_value.users_firstname_MAX_LENGTH &&
			user.getPassword().matches(mysql_validation_value.users_firstname_STRING_RANGE) &&
			
			user.getLastname() !=null &&
			user.getLastname().length() <= mysql_validation_value.users_lastname_MAX_LENGTH &&
			user.getLastname().matches(mysql_validation_value.users_lastname_STRING_RANGE) &&
			
			user.getEmail() !=null &&
			user.getEmail().length() <= mysql_validation_value.users_email_MAX_LENGTH &&
			user.getEmail().matches(mysql_validation_value.users_email_STRING_RANGE)
		){ return true;}
		else
		{ return false;}
	}

	@Transactional
	public boolean Check_User_Exist(String username) {
		try{
			if(userdao.get_by_username(username)!=null)
				return true;
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	@Transactional
	public boolean Check_Email_Exist(String email) {
		try{
			if(userdao.get_by_email(email)!=null)
				return true;
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	@Transactional
	public users Insert_user(users user) {	
		try{
			userdao.save(user);
			user = userdao.get_by_username(user.getUsername());
			if(user!=null){return user;}
			else{return null;}
		}catch(Exception e){
			return null;
		}
	}
	
	@Transactional
	public users get_user_by_username(String username) {
		users user;
		try{
			user = userdao.get_by_username(username);
		}catch(Exception e){
			return null;
		}
		return user;
	}

	@Transactional
	public boolean Create_token() {
		// TODO Auto-generated method stub
		return false;
	}

}
