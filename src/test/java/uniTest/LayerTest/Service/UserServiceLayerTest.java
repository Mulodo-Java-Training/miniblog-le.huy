package uniTest.LayerTest.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserSignupForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;
import mini.service.TokenServiceInterface;
import mini.service.UserServiceInterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class UserServiceLayerTest
{

    @Autowired
    private UserServiceInterface user_service;

    @Autowired
    private TokenServiceInterface token_service;

    @Test
    public void UserController_CreateUser_service()
    {

        assertEquals(true, user_service.deleteUser("ledanghuytest"));
        UserSignupForm user = new UserSignupForm();
        user.username = "ledanghuytest";
        user.email = "test@mulodo.com";
        user.firstname = "le";
        user.lastname = "huy";
        user.password = "testpass";
        assertEquals(true, user_service.insertUser(user.set_sign_up_data()));
        assertEquals(true, user_service.checkUserExist("ledanghuytest"));
        assertEquals(true, user_service.checkEmailExist("test@mulodo.com"));
        assertEquals(true, user_service.deleteUser("ledanghuytest"));
        assertEquals(false, user_service.checkUserExist("ledanghuytest"));
        assertEquals(false, user_service.checkEmailExist("test@mulodo.com"));

    }

    @Test
    public void UserController_Login_Logout_Update_ChangepassUser_service()
    {

        assertEquals(true, user_service.deleteUser("ledanghuytest"));
        UserSignupForm user = new UserSignupForm();
        user.username = "ledanghuytest";
        user.email = "test@mulodo.com";
        user.firstname = "le";
        user.lastname = "huy";
        user.password = "testpass";
        assertEquals(true, user_service.insertUser(user.set_sign_up_data()));

        UserLoginForm userlogin = new UserLoginForm();
        userlogin.username = "ledanghuytest";
        userlogin.password = "testpass";

        Token token = user_service.loginUser(userlogin);
        assertEquals(64, token.getAccess_token().length());

        token_service.checkAccessToken(token.getAccess_token());
        UserUpdateForm userupdate = new UserUpdateForm();
        userupdate.firstname = "bla";
        userupdate.lastname = "blabla";

        assertEquals(false, user_service.updateUserInfo(userupdate, token.getUserId()));
        userupdate.password = "testpass";
        assertEquals(true, user_service.updateUserInfo(userupdate, token.getUserId()));
        Users userup = user_service.getUserById(token.getUserId());
        assertEquals(userup.getFirstname(), "bla");
        assertEquals(userup.getLastname(), "blabla");

        userlogin.password = "testpassfalse";
        assertEquals(null, user_service.loginUser(userlogin));

        UserChangePasswordForm userpass = new UserChangePasswordForm();
        userpass.old_password = "testpassfalse";
        userpass.new_password = "testpassfalse";
        assertEquals(null, user_service.changePassword(userpass, token.getUserId()));
        userpass.old_password = "testpass";
        token = user_service.changePassword(userpass, token.getUserId());
        assertEquals(64, token.getAccess_token().length());

        assertEquals(true, user_service.logoutUser(token));
        assertEquals(true, user_service.deleteUser("ledanghuytest"));
    }

    @Test
    public void UserController_getUser_service()
    {

        assertEquals(true, user_service.deleteUser("ledanghuytest"));
        UserSignupForm user = new UserSignupForm();
        user.username = "ledanghuytest";
        user.email = "test@mulodo.com";
        user.firstname = "le";
        user.lastname = "huy";
        user.password = "testpass";
        assertEquals(true, user_service.insertUser(user.set_sign_up_data()));

        UserLoginForm userlogin = new UserLoginForm();
        userlogin.username = "ledanghuytest";
        userlogin.password = "testpass";

        Token token = user_service.loginUser(userlogin);
        assertEquals(64, token.getAccess_token().length());
        Users test = user_service.getUserByUsername("ledanghuytest");
        assertEquals("le", test.getFirstname());
        assertTrue(user_service.searchUserByUsername("huy").size() > 1);

        assertEquals(true, user_service.logoutUser(token));
        assertEquals(true, user_service.deleteUser("ledanghuytest"));
    }

    @Test
    public void UserController_error()
    {
        UserSignupForm user = new UserSignupForm();
        user.username = "ledanghuytest";
        user.email = "test@mulodo.com";
        user.firstname = "le";
        user.lastname = "huy";
        user.password = "testpass";
        try {
            assertEquals(false, user_service.insertUser(new Users()));

        } catch (Exception e) {
        }
    }
}
