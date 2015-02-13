package mini.form.response;

import java.util.Date;

import mini.model.users;

public class UserFull {

	public int id;
	public String username;
	public String email;
	public String firstname;
	public String lastname;
	public Date create_at;
	public Date modified_at;

	public UserFull(users user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.create_at = user.getCreate_at();
		this.modified_at = user.getModified_at();
	}
}
