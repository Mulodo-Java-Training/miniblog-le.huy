package mini.form.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.model.users;
import mini.systemvalue.SystemValue;
import mini.util.HashGenUtil;

public class UserSignupForm {
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
	@NotNull
	@Size(max = SystemValue.FIRSTNAME_MAX_LENGTH)
	@Pattern(regexp = SystemValue.FIRSTNAME_STRING_RANGE)
	@FormParam("firstname")
	public String firstname;
	@NotNull
	@Size(max = SystemValue.LASTNAME_MAX_LENGTH)
	@Pattern(regexp = SystemValue.LASTNAME_STRING_RANGE)
	@FormParam("lastname")
	public String lastname;
	@NotNull
	@Size(max = SystemValue.EMAIL_MAX_LENGTH)
	@Pattern(regexp = SystemValue.EMAIL_STRING_RANGE)
	@FormParam("email")
	public String email;

	public users set_sign_up_data() {
		users user = new users();
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
