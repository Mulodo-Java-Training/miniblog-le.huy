package mini.service;

import mini.model.Token;
import mini.model.Users;

/**
 * @author Le Dang Huy
 *
 */

public interface TokenServiceInterface
{

    public Token checkAccessToken(String access_token);

    public Token createToken(Users user);

    public boolean clearTokenTalbeByUserId(int user_id);
}
