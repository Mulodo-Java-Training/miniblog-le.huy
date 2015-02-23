package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.model.Users;
import mini.systemvalue.SystemValue;
import mini.util.HashGenUtil;

import org.hibernate.validator.constraints.Email;

/**
 * @author Le Dang Huy
 *
 */

public class UserSignupForm
{

    @NotNull
    @Pattern ( regexp = SystemValue.USERNAME_STRING_RANGE )
    @FormParam ( "username" )
    public String username;

    @NotNull
    @Pattern ( regexp = SystemValue.PASSWORD_STRING_RANGE )
    @FormParam ( "password" )
    public String password;

    @NotNull
    @Pattern ( regexp = SystemValue.FIRSTNAME_STRING_RANGE )
    @FormParam ( "firstname" )
    public String firstname;

    @NotNull
    @Pattern ( regexp = SystemValue.LASTNAME_STRING_RANGE )
    @FormParam ( "lastname" )
    public String lastname;

    @NotNull
    @Email
    @FormParam ( "email" )
    public String email;

    public Users set_sign_up_data()
    {

        Users user = new Users();
        user.setPassword(HashGenUtil.Encrypt_password(password));
        user.setUsername(this.username);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setEmail(this.email);
        user.setCreate_at(Calendar.getInstance().getTime());
        user.setModified_at(Calendar.getInstance().getTime());
        return user;
    }
}
