package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

public class UserLoginForm {
	@NotNull
	@Size(min = SystemValue.USERNAME_MIN_LENGTH, max = SystemValue.USERNAME_MAX_LENGTH)
	@Pattern(regexp = SystemValue.USERNAME_STRING_RANGE)
	@FormParam("username")
	public String username;
	@NotNull
	@Size(min = SystemValue.PASSWORD_MIN_LENGTH, max = SystemValue.PASSWORD_MAX_LENGTH)
	@Pattern(regexp = SystemValue.PASSWORD_STRING_RANGE)
	@FormParam("password")
	public String password;
}
