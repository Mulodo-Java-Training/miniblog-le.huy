package mini.form.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import mini.systemvalue.SystemValue;

public class UserUpdateForm {
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
}
