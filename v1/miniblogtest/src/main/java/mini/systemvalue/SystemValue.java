package mini.systemvalue;

public class SystemValue {
	//validation value
	public final static int USERNAME_MIN_LENGTH=6;
	public final static int USERNAME_MAX_LENGTH=32;
	public final static String USERNAME_STRING_RANGE="[a-z0-9 ]*";
	
	public final static int PASSWORD_MIN_LENGTH=6;
	public final static int PASSWORD_MAX_LENGTH=72;
	public final static String PASSWORD_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int FIRSTNAME_MAX_LENGTH=32;
	public final static String FIRSTNAME_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int LASTNAME_MAX_LENGTH=32;
	public final static String LASTNAME_STRING_RANGE="[A-Za-z0-9 ]*";
	
	public final static int EMAIL_MAX_LENGTH=254;
	public final static String EMAIL_STRING_RANGE="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public final static int ACCESS_TOKEN_LENGTH=64;
	
	public final static int POST_TITLE_MIN_LENGTH=10;
	public final static int POST_TITLE_MAX_LENGTH=100;
	
	public final static int POST_CONTENT_MIN_LENGTH=10;
	public final static int POST_CONTENT_MAX_LENGTH=2048;

	public final static int COMMENT_MAX_LENGTH=254;
	public final static int COMMENT_MIN_LENGTH=1;
	//System Secret key
	public final static String SECRET_KEY="XfLTaYmtz9";
	public final static int TOKEN_EXPIRE_TIME=1;
	
//==================Error code list================================
	public final static int CODE_200=200;
	//Validate data error.
	public final static int ERRORCODE_1001=1001;
	
	//username already exist
	public final static int ERRORCODE_2001=2001;
	//email already exist
	public final static int ERRORCODE_2002=2002;
	//create user fail
	public final static int ERRORCODE_2003=2003;
	//Incorrect username or password
	public final static int ERRORCODE_2004=2004;
	//logout fail
	public final static int ERRORCODE_2005=2005;
	//userinfo update fail
	public final static int ERRORCODE_2006=2006;
	//change password fail
	public final static int ERRORCODE_2007=2007;
	//invalid or expired token
	public final static int ERRORCODE_2008=2008;
	//username not exist
	public final static int ERRORCODE_2009=2009;
	
	
	//post create error
	public final static int ERRORCODE_3001=3001;
	//change post status error
	public final static int ERRORCODE_3002=3002;
	//update post error
	public final static int ERRORCODE_3003=3003;
	//delete post error
	public final static int ERRORCODE_3004=3004;
	//post id not exist
	public final static int ERRORCODE_3005=3005;

	//create comment error
	public final static int ERRORCODE_4001=4001;
	//edit comment error
	public final static int ERRORCODE_4002=4002;
	//delete comment error
	public final static int ERRORCODE_4003=4003;
	//invalid comment id
	public final static int ERRORCODE_4004=4004;

}
