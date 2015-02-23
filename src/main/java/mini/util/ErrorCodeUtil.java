package mini.util;

import mini.systemvalue.SystemValue;

/**
 * @author Le Dang Huy
 */
public class ErrorCodeUtil
{

    public final static ReturnObject set_error_code(int code)
    {

        ReturnObject resp = new ReturnObject();
        switch (code) {
        case SystemValue.ERRORCODE_1001:
            resp.meta.code = SystemValue.ERRORCODE_1001;
            resp.meta.description = "Validate Data Error.";
            break;

        case SystemValue.ERRORCODE_2001:
            resp.meta.code = SystemValue.ERRORCODE_2001;
            resp.meta.description = "Username Already Exist";
            break;

        case SystemValue.ERRORCODE_2002:
            resp.meta.code = SystemValue.ERRORCODE_2002;
            resp.meta.description = "Email Already Exist";
            break;

        case SystemValue.ERRORCODE_2003:
            resp.meta.code = SystemValue.ERRORCODE_2003;
            resp.meta.description = "create user fail";
            break;

        case SystemValue.ERRORCODE_2004:
            resp.meta.code = SystemValue.ERRORCODE_2004;
            resp.meta.description = "Incorrect username or password";
            break;

        case SystemValue.ERRORCODE_2005:
            resp.meta.code = SystemValue.ERRORCODE_2005;
            resp.meta.description = "Logout error";
            break;

        case SystemValue.ERRORCODE_2006:
            resp.meta.code = SystemValue.ERRORCODE_2006;
            resp.meta.description = "userinfo update fail";
            break;

        case SystemValue.ERRORCODE_2007:
            resp.meta.code = SystemValue.ERRORCODE_2007;
            resp.meta.description = "change password fail";
            break;

        case SystemValue.ERRORCODE_2008:
            resp.meta.code = SystemValue.ERRORCODE_2008;
            resp.meta.description = "invalid or expired token";
            break;

        case SystemValue.ERRORCODE_2009:
            resp.meta.code = SystemValue.ERRORCODE_2009;
            resp.meta.description = "user not exist";
            break;

        case SystemValue.ERRORCODE_3001:
            resp.meta.code = SystemValue.ERRORCODE_3001;
            resp.meta.description = "create post error";
            break;

        case SystemValue.ERRORCODE_3002:
            resp.meta.code = SystemValue.ERRORCODE_3002;
            resp.meta.description = "change post status error";
            break;

        case SystemValue.ERRORCODE_3003:
            resp.meta.code = SystemValue.ERRORCODE_3003;
            resp.meta.description = "update post error";
            break;

        case SystemValue.ERRORCODE_3004:
            resp.meta.code = SystemValue.ERRORCODE_3004;
            resp.meta.description = "delete post error";
            break;

        case SystemValue.ERRORCODE_3005:
            resp.meta.code = SystemValue.ERRORCODE_3005;
            resp.meta.description = "post id not valid";
            break;

        case SystemValue.ERRORCODE_4001:
            resp.meta.code = SystemValue.ERRORCODE_4001;
            resp.meta.description = "create comment error";
            break;

        case SystemValue.ERRORCODE_4002:
            resp.meta.code = SystemValue.ERRORCODE_4002;
            resp.meta.description = "edit comment error";
            break;

        case SystemValue.ERRORCODE_4003:
            resp.meta.code = SystemValue.ERRORCODE_4003;
            resp.meta.description = "delete comment error";
            break;

        }
        return resp;
    }

    public final static ReturnObject set_code(int code, String description, Object data)
    {

        ReturnObject resp = new ReturnObject();
        resp.meta.code = code;
        resp.meta.description = description;
        resp.data = data;
        return resp;
    }
}
