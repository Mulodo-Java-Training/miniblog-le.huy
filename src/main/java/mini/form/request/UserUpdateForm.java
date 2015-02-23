package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 *
 */

public class UserUpdateForm
{

    @NotNull
    @Pattern ( regexp = SystemValue.FIRSTNAME_STRING_RANGE )
    @FormParam ( "firstname" )
    public String firstname;

    @NotNull
    @Pattern ( regexp = SystemValue.LASTNAME_STRING_RANGE )
    @FormParam ( "lastname" )
    public String lastname;

    @NotNull
    @Pattern ( regexp = SystemValue.PASSWORD_STRING_RANGE )
    @FormParam ( "password" )
    public String password;
}
