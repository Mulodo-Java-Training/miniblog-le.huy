package mini.systemvalue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Le Dang Huy
 */
public class SystemValue
{

    // validation value
    public final static String USERNAME_STRING_RANGE = "[a-z0-9]{6,32}";

    public final static String PASSWORD_STRING_RANGE = "[A-Za-z0-9]{6,72}";

    public final static String FIRSTNAME_STRING_RANGE = "[A-Za-z0-9]{1,32}";

    public final static String LASTNAME_STRING_RANGE = "[A-Za-z0-9]{1,32}";

    public final static String ACCESS_TOKEN_STRING_RANGE = "[a-f0-9]{64}";

    public final static String USERNAME_SEARCH_RANGE = "[a-z0-9]{1,32}";

    public final static String POST_TITLE_STRING_RANGE = "[\\p{Print}&&[^~,]]{10,100}";

    public final static String POST_TITLE_SEARCH_RANGE = "[\\p{Print}&&[^~,]]{1,100}";

    public final static String POST_CONTENT_STRING_RANGE = "[\\p{Print}&&[^~,]]{10,2048}";

    public final static String COMMENT_STRING_RANGE = "[\\p{Print}&&[^~,]]{1,254}";

    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String USER_GET_MODE_CURRENT = "current";

    public final static String USER_GET_MODE_ID = "id";

    @SuppressWarnings("serial")
    public final static List<String> USER_GET_MODE = new ArrayList<String>() {

        {
            add(USER_GET_MODE_CURRENT);
            add(USER_GET_MODE_ID);
        }
    };

    public final static String POST_GET_MODE_CURRENT = "current";

    public final static String POST_GET_MODE_ALL = "all";

    public final static String POST_GET_MODE_USERID = "userid";

    public final static String POST_GET_MODE_POSTID = "postid";

    public final static String POST_GET_MODE_TOP = "top";

    public final static String POST_GET_MODE_TITLE = "title";

    @SuppressWarnings("serial")
    public final static List<String> POST_GET_MODE = new ArrayList<String>() {

        {
            add(POST_GET_MODE_CURRENT);
            add(POST_GET_MODE_ALL);
            add(POST_GET_MODE_USERID);
            add(POST_GET_MODE_POSTID);
            add(POST_GET_MODE_TOP);
            add(POST_GET_MODE_TITLE);
        }
    };

    public final static String COMMENT_GET_MODE_CURRENT = "current";

    public final static String COMMENT_GET_MODE_ID = "id";

    public final static String COMMENT_GET_MODE_USERID = "userid";

    public final static String COMMENT_GET_MODE_POSTID = "postid";

    @SuppressWarnings("serial")
    public final static List<String> COMMENT_GET_MODE = new ArrayList<String>() {

        {
            add(COMMENT_GET_MODE_CURRENT);
            add(COMMENT_GET_MODE_ID);
            add(COMMENT_GET_MODE_USERID);
            add(COMMENT_GET_MODE_POSTID);
        }
    };

    // System Secret key
    public final static String SECRET_KEY = "XfLTaYmtz9";

    public final static int TOKEN_EXPIRE_TIME = 1;

    // ==================Error code list================================
    public final static int CODE_200 = 200;

    // Validate data error.
    public final static int ERRORCODE_1001 = 1001;

    // username already exist
    public final static int ERRORCODE_2001 = 2001;

    // email already exist
    public final static int ERRORCODE_2002 = 2002;

    // create user fail
    public final static int ERRORCODE_2003 = 2003;

    // Incorrect username or password
    public final static int ERRORCODE_2004 = 2004;

    // logout fail
    public final static int ERRORCODE_2005 = 2005;

    // userinfo update fail
    public final static int ERRORCODE_2006 = 2006;

    // change password fail
    public final static int ERRORCODE_2007 = 2007;

    // invalid or expired token
    public final static int ERRORCODE_2008 = 2008;

    // username not exist
    public final static int ERRORCODE_2009 = 2009;

    // post create error
    public final static int ERRORCODE_3001 = 3001;

    // change post status error
    public final static int ERRORCODE_3002 = 3002;

    // update post error
    public final static int ERRORCODE_3003 = 3003;

    // delete post error
    public final static int ERRORCODE_3004 = 3004;

    // post id not exist
    public final static int ERRORCODE_3005 = 3005;

    // create comment error
    public final static int ERRORCODE_4001 = 4001;

    // edit comment error
    public final static int ERRORCODE_4002 = 4002;

    // delete comment error
    public final static int ERRORCODE_4003 = 4003;

    // invalid comment id
    public final static int ERRORCODE_4004 = 4004;

}
