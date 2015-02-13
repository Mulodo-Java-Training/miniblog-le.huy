package mini.systemvalue;

public class TableCollumName {
	//==================table spec list================================
		//users table
		public final static String USERS_ID = "id";
		public final static String USERS_USERNAME = "username";
		public final static String USERS_PASSWORD = "password";
		public final static String USERS_EMAIL = "email";
		public final static String USERS_FIRSTNAME = "firstname";
		public final static String USERS_LASTNAME = "lastname";
		public final static String USERS_CREATE_AT = "create_at";
		public final static String USERS_MODIFIED_AT = "modified_at";
		
		//token table
		public final static String TOKEN_ID = "id";
		public final static String TOKEN_ACCESS_TOKEN = "access_token";
		public final static String TOKEN_CREATE_AT = "create_at";
		public final static String TOKEN_EXPIRED = "expired";
		public final static String TOKEN_USER_ID = "user_id";
		public final static String TOKEN_USER_ID_FOREIGN = ".user.id";
		
		//posts table
		public final static String POSTS_ID = "id";
		public final static String POSTS_TITLE = "title";
		public final static String POSTS_CONTENT = "content";
		public final static String POSTS_STATUS = "status";
		public final static String POSTS_CREATE_AT = "create_at";
		public final static String POSTS_MODIFIED_AT = "modified_at";
		public final static String POSTS_USER_ID = "user_id";
		public final static String POSTS_USER_ID_FOREIGN = ".user.id";
		
		//comment table
		public final static String COMMENTS_ID = "id";
		public final static String COMMENTS_COMMENT = "comment";
		public final static String COMMENTS_CREATE_AT = "create_at";
		public final static String COMMENTS_MODIFIED_AT = "modified_at";
		public final static String COMMENTS_POST_ID = "post_id";
		public final static String COMMENTS_POST_ID_FOREIGN = ".post.id";
		public final static String COMMENTS_USER_ID = "user_id";
		public final static String COMMENTS_USER_ID_FOREIGN = ".user.id";

}
