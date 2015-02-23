package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 *
 */

public class UserLoginForm
{

    @NotNull
    @Pattern ( regexp = SystemValue.USERNAME_STRING_RANGE )
    @FormParam ( "username" )
    public String username;

    @NotNull
    @Pattern ( regexp = SystemValue.PASSWORD_STRING_RANGE )
    @FormParam ( "password" )
    public String password;
}
