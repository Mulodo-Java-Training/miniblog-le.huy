package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

public class UserChangePasswordForm {
	@NotNull
	@Size(min = SystemValue.PASSWORD_MIN_LENGTH, max = SystemValue.PASSWORD_MAX_LENGTH)
	@Pattern(regexp = SystemValue.PASSWORD_STRING_RANGE)
	@FormParam("old_password")
	public String old_password;
	@NotNull
	@Size(min = SystemValue.PASSWORD_MIN_LENGTH, max = SystemValue.PASSWORD_MAX_LENGTH)
	@Pattern(regexp = SystemValue.PASSWORD_STRING_RANGE)
	@FormParam("new_password")
	public String new_password;
}
