package unitTest.testResource.Controller;

import org.springframework.stereotype.Service;

import mini.model.Token;
import mini.model.Users;
import mini.service.TokenServiceInterface;

@Service
public class TokenServiceTest implements TokenServiceInterface
{

    @Override
    public Token checkAccessToken(String access_token)
    {

        Users user = new Users();
        Token token = new Token();
        switch (access_token)
        {
        case "token2008":
            return null;
        case "logout2005":
            token.setAccess_token("logout2005");
            break;
        case "logout200":
            token.setAccess_token("logout200");
            break;
        case "updateuser2006":
            token.setAccess_token("updateuser2006");
            user.setId(1);
            token.setUser(user);
            break;
        case "updateuser200":
            token.setAccess_token("updateuser200");
            user.setId(1);
            token.setUser(user);
            break;
        case "passuser2007":
            token.setAccess_token("getuser200");
            user.setUsername("getuser200");
            user.setId(1);
            token.setUser(user);
            break;
        case "getuser200":
            token.setAccess_token("getuser200");
            user.setUsername("getuser200");
            user.setId(1);
            token.setUser(user);
            break;
        case "getuser2009":
            token.setUser(null);
            break;
        case "getuser2009invalidid":
            user.setUsername("getuser200");
            user.setId(2);
            token.setUser(user);
            break;
        default:
            user.setUsername("validtoken");
            user.setId(1);
            token.setUser(user);
            break;
        }
        return token;
    }

    @Override
    public Token createToken(Users user)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean clearTokenTalbeByUserId(int user_id)
    {

        // TODO Auto-generated method stub
        return false;
    }

}
