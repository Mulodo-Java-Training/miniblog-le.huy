package mini.service;

import mini.model.token;
import mini.model.users;

public interface TokenServiceInterface {
	public token checkAccessToken(String access_token);
	public String createToken(users user);
	public boolean clearTokenTalbeByUserId(int user_id);
}
