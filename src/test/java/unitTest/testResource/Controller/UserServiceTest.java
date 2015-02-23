package unitTest.testResource.Controller;

import java.util.Date;
import java.util.List;

import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;
import mini.service.UserServiceInterface;

import org.springframework.stereotype.Service;

@Service
public class UserServiceTest implements UserServiceInterface
{

    @Override
    public boolean checkUserExist(String username)
    {

        switch (username) {
        case "createuser2001":
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExist(String email)
    {

        switch (email) {
        case "createuser2002":
            return true;
        }
        return false;
    }

    @Override
    public Users getUserByUsername(String username)
    {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteUser(String username)
    {

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertUser(Users user)
    {

        switch (user.getUsername()) {
        case "createuser2003":
            return false;
        }
        return true;
    }

    @Override
    public Token loginUser(UserLoginForm data)
    {

        Token token = new Token();
        switch (data.username) {
        case "login200":
            token.setAccess_token("login200");
            break;
        case "login2004":
            token = null;
            break;
        }

        return token;
    }

    @Override
    public boolean logoutUser(Token token)
    {

        switch (token.getAccess_token()) {
        case "logout2005":
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserInfo(UserUpdateForm data, int user_id)
    {

        switch (data.firstname) {
        case "updateuser2006":
            return false;
        }
        return true;
    }

    @Override
    public Users getUserById(int id)
    {

        Users user = new Users();
        if (id == 1) {
            user.setUsername("getuser200");
            user.setId(1);
            user.setCreate_at(new Date());
            user.setModified_at(new Date());
            user.setFirstname("le");
            user.setLastname("whatever");
            user.getCreate_at();
            user.getModified_at();
            user.getFirstname();
            user.getLastname();
            user.getPassword();
            user.getEmail();
            return user;
        }
        return null;
    }

    @Override
    public Token changePassword(UserChangePasswordForm data, int user_id)
    {

        Token token = new Token();

        if (data.new_password == "passuser2007") {
            return null;
        }
        if (data.new_password == "passuser200") {
            token.setAccess_token("passuser200");
        }
        return token;
    }

    @Override
    public List searchUserByUsername(String query)
    {

        // TODO Auto-generated method stub
        return null;
    }

}
