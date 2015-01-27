package mini.resteasy.form;

import javax.ws.rs.FormParam;

public class signupform {
	@FormParam("username")
	private String username;
	@FormParam("password")
	private String password;
	@FormParam("firstname")
	private String firstname;
	@FormParam("lastname")
	private String lastname;
	@FormParam("email")
	private String email;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
