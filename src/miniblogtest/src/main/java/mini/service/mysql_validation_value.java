package mini.service;

public class mysql_validation_value {
	public final static int users_username_MIN_LENGTH=6;
	public final static int users_username_MAX_LENGTH=32;
	public final static String users_username_STRING_RANGE="[a-z0-9 ]*";
	
	public final static int users_password_MIN_LENGTH=6;
	public final static int users_password_MAX_LENGTH=72;
	public final static String users_password_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_firstname_MAX_LENGTH=32;
	public final static String users_firstname_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_lastname_MAX_LENGTH=32;
	public final static String users_lastname_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int users_email_MAX_LENGTH=254;
	public final static String users_email_STRING_RANGE="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	
}
