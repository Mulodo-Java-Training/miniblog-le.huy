package unitTest.LayerTest.Controller;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import mini.controller.UserController;
import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserSignupForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;
import mini.systemvalue.SystemValue;
import mini.util.ReturnObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( "file:src/test/java/TestControllerContext.xml" )
public class UserControllerLayerTest
{

    @Autowired
    private UserController user_controller;

    @Test
    public void UserController_CreateUser_2001()
    {

        UserSignupForm signupform = new UserSignupForm();
        signupform.username = "createuser2001";
        Response respone = user_controller.createUser(signupform);
        assertEquals(SystemValue.ERRORCODE_2001, respone.getStatus());
    }

    @Test
    public void UserController_CreateUser_2002()
    {

        UserSignupForm signupform = new UserSignupForm();
        signupform.username = "createuser2002";
        signupform.email = "createuser2002";
        Response respone = user_controller.createUser(signupform);
        assertEquals(SystemValue.ERRORCODE_2002, respone.getStatus());
    }

    @Test
    public void UserController_CreateUser_2003()
    {

        UserSignupForm signupform = new UserSignupForm();
        signupform.username = "createuser2003";
        signupform.email = "createuser2003";
        Response respone = user_controller.createUser(signupform);
        assertEquals(SystemValue.ERRORCODE_2003, respone.getStatus());
    }

    @Test
    public void UserController_CreateUser_200()
    {

        UserSignupForm signupform = new UserSignupForm();
        signupform.username = "createuser200";
        signupform.email = "createuser200";
        Response respone = user_controller.createUser(signupform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void UserController_loginUser_200()
    {

        UserLoginForm loginform = new UserLoginForm();
        loginform.username = "login200";
        Response respone = user_controller.loginUser(loginform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
        ReturnObject res = (ReturnObject) respone.getEntity();
        Token tok = (Token) res.data;
        assertEquals("login200", tok.getAccess_token());
    }

    @Test
    public void UserController_loginUser_2004()
    {

        UserLoginForm loginform = new UserLoginForm();
        loginform.username = "login2004";
        Response respone = user_controller.loginUser(loginform);
        assertEquals(SystemValue.ERRORCODE_2004, respone.getStatus());
    }

    @Test
    public void UserController_logoutUser_2008()
    {

        Response respone = user_controller.logoutUser("token2008");
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void UserController_logoutUser_2005()
    {

        Response respone = user_controller.logoutUser("logout2005");
        assertEquals(SystemValue.ERRORCODE_2005, respone.getStatus());
    }

    @Test
    public void UserController_logoutUser_200()
    {

        Response respone = user_controller.logoutUser("logout200");
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void UserController_updateUser_2008()
    {

        Response respone = user_controller.updateUser("token2008", null);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void UserController_updateUser_2006()
    {

        UserUpdateForm updateform = new UserUpdateForm();
        updateform.firstname = "updateuser2006";
        Response respone = user_controller.updateUser("updateuser2006",
                updateform);
        assertEquals(SystemValue.ERRORCODE_2006, respone.getStatus());
    }

    @Test
    public void UserController_updateUser_200()
    {

        UserUpdateForm updateform = new UserUpdateForm();
        updateform.firstname = "updateuser200";
        Response respone = user_controller.updateUser("updateuser200",
                updateform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
    }

    @Test
    public void UserController_getUser_1001()
    {

        Response respone = user_controller.getUserInfo("", "invalidmode", 1);
        assertEquals(SystemValue.ERRORCODE_1001, respone.getStatus());
    }

    @Test
    public void UserController_getUser_2008()
    {

        Response respone = user_controller.getUserInfo("token2008", "id", 1);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void UserController_getUser_current_2009()
    {

        Response respone = user_controller.getUserInfo("getuser2009",
                "current", 1);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void UserController_getUser_current_200()
    {

        Response respone = user_controller.getUserInfo("getuser200", "current",
                1);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
        ReturnObject res = (ReturnObject) respone.getEntity();
        Users user = (Users) res.data;
        assertEquals("getuser200", user.getUsername());
    }

    @Test
    public void UserController_getUser_id_2009()
    {

        Response respone = user_controller.getUserInfo("getuser2009", "id", 10);
        assertEquals(SystemValue.ERRORCODE_2009, respone.getStatus());
    }

    @Test
    public void UserController_getUser_id_200()
    {

        Response respone = user_controller.getUserInfo("getuser200", "id", 1);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
        ReturnObject res = (ReturnObject) respone.getEntity();
        Users user = (Users) res.data;
        assertEquals("getuser200", user.getUsername());
    }

    @Test
    public void UserController_changePassUser_2008()
    {

        UserChangePasswordForm passform = new UserChangePasswordForm();
        Response respone = user_controller.changeUserPassword("token2008",
                passform);
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());
    }

    @Test
    public void UserController_changePassUser_2007()
    {

        UserChangePasswordForm passform = new UserChangePasswordForm();
        passform.new_password = "passuser2007";
        Response respone = user_controller.changeUserPassword("passuser2007",
                passform);
        assertEquals(SystemValue.ERRORCODE_2007, respone.getStatus());
    }

    @Test
    public void UserController_changePassUser_200()
    {

        UserChangePasswordForm passform = new UserChangePasswordForm();
        passform.new_password = "passuser200";
        Response respone = user_controller.changeUserPassword("passuser200",
                passform);
        assertEquals(SystemValue.CODE_200, respone.getStatus());
        ReturnObject res = (ReturnObject) respone.getEntity();
        Token token = (Token) res.data;
        assertEquals("passuser200", token.getAccess_token());
    }

    @Test
    public void UserController_searchUser_2008()
    {

        Response respone = user_controller.searchUserByName("token2008", "abc");
        assertEquals(SystemValue.ERRORCODE_2008, respone.getStatus());

    }

    @Test
    public void UserController_searchUser_200()
    {

        Response respone = user_controller.searchUserByName("searchuser200",
                "abc");
        assertEquals(SystemValue.CODE_200, respone.getStatus());

    }
}
