package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 */
public class UserChangePasswordForm
{

    @NotNull
    @Pattern(regexp = SystemValue.PASSWORD_STRING_RANGE)
    @FormParam("old_password")
    public String old_password;

    @NotNull
    @Pattern(regexp = SystemValue.PASSWORD_STRING_RANGE)
    @FormParam("new_password")
    public String new_password;

}
