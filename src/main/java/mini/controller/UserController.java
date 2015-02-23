package mini.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mini.form.request.UserChangePasswordForm;
import mini.form.request.UserLoginForm;
import mini.form.request.UserSignupForm;
import mini.form.request.UserUpdateForm;
import mini.model.Token;
import mini.model.Users;
import mini.service.TokenServiceInterface;
import mini.service.UserServiceInterface;
import mini.systemvalue.SystemValue;
import mini.util.ErrorCodeUtil;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.plugins.validation.hibernate.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Le Dang Huy
 *
 */

@Controller
@Path ( "/v1/user" )
@Produces ( MediaType.APPLICATION_JSON )
@ValidateRequest
public class UserController
{

    @Autowired
    private UserServiceInterface user_service;

    @Autowired
    private TokenServiceInterface token_service;

    /**
     * @param data
     * @return
     */
    
    @POST
    @Path ( "adduser" )
    @Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
    public Response createUser(
    @Form
    @Valid
    UserSignupForm data)
    {

        if (user_service.checkUserExist(data.username))
        {
            return Response
                    .status(SystemValue.ERRORCODE_2001)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2001))
                    .build();
        }

        if (user_service.checkEmailExist(data.email))
        {
            return Response
                    .status(SystemValue.ERRORCODE_2002)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2002))
                    .build();
        }

        Users user = data.set_sign_up_data();

        if (!user_service.insertUser(user))
        {
            return Response
                    .status(SystemValue.ERRORCODE_2003)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2003))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "User account was created successfully.", null))
                .build();
    }

    /**
     * @param data
     * @return
     */
    
    @POST
    @Path ( "login" )
    @Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
    public Response loginUser(
    @Form
    @Valid
    UserLoginForm data)
    {

        Token token = user_service.loginUser(data);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2004)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2004))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "Login successful!", token)).build();
    }

    /**
     * @param access_token
     * @return
     */
    
    @POST
    @Path ( "logout" )
    public Response logoutUser(
            @Pattern ( regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE )
            @HeaderParam ( "access_token" )
            String access_token)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2008))
                    .build();
        }

        if (!user_service.logoutUser(token))
        {
            return Response
                    .status(SystemValue.ERRORCODE_2005)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2005))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "Logout successful!", null)).build();
    }

    /**
     * @param access_token
     * @param data
     * @return
     */
    
    @PUT
    @Path ( "user" )
    @Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
    public Response updateUser(
            @Pattern ( regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE )
            String access_token,
            @Form
            @Valid
            UserUpdateForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2008))
                    .build();
        }

        if (!user_service.updateUserInfo(data, token.getUser().getId()))
        {
            return Response
                    .status(SystemValue.ERRORCODE_2006)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2006))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "Update user info successful!", null)).build();
    }

    /**
     * @param access_token
     * @param mode
     * @param id
     * @return
     */
    
    @GET
    @Path ( "user" )
    public Response getUserInfo(
            @Pattern ( regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE )
            @HeaderParam ( "access_token" )
            String access_token,
            @QueryParam ( "mode" )
            String mode,
            @QueryParam ( "id" )
            int id)
    {

        if (!SystemValue.USER_GET_MODE.contains(mode))
        {
            return Response
                    .status(SystemValue.ERRORCODE_1001)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_1001))
                    .build();
        }

        Users user;
        Token token = token_service.checkAccessToken(access_token);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2008))
                    .build();
        }

        Response resp = Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil
                        .set_code(SystemValue.CODE_200, null, null)).build();
        if (mode == SystemValue.USER_GET_MODE_CURRENT)
        {
            user = token.getUser();

            if (user == null)
            {
                resp = Response
                        .status(SystemValue.ERRORCODE_2009)
                        .entity(ErrorCodeUtil
                                .set_error_code(SystemValue.ERRORCODE_2009))
                        .build();
            } else
            {
                resp = Response
                        .status(SystemValue.CODE_200)
                        .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                                "Info of current user", user)).build();
            }
        }
        if (mode == SystemValue.USER_GET_MODE_ID)
        {
            user = user_service.getUserById(id);

            if (user == null)
            {
                resp = Response
                        .status(SystemValue.ERRORCODE_2009)
                        .entity(ErrorCodeUtil
                                .set_error_code(SystemValue.ERRORCODE_2009))
                        .build();
            } else
            {
                resp = Response
                        .status(SystemValue.CODE_200)
                        .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                                "Info of user by id", user)).build();
            }
        }
        return resp;
    }

    @PUT
    @Path ( "pass" )
    @Consumes ( MediaType.APPLICATION_FORM_URLENCODED )
    public Response changeUserPassword(
            @Pattern ( regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE )
            @HeaderParam ( "access_token" )
            String access_token,
            @Form
            @Valid
            UserChangePasswordForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2008))
                    .build();
        }

        token = user_service.changePassword(data, token.getUser().getId());

        if (null == token)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2007)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2007))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "Password Change successful!", token)).build();
    }

    @GET
    @Path ( "search/{query}" )
    public Response searchUserByName(
            @Pattern ( regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE )
            @HeaderParam ( "access_token" )
            String access_token,
            @Pattern ( regexp = SystemValue.USERNAME_SEARCH_RANGE )
            @PathParam ( "query" )
            String query)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null)
        {
            return Response
                    .status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil
                            .set_error_code(SystemValue.ERRORCODE_2008))
                    .build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                        "result return",
                        user_service.searchUserByUsername(query))).build();
    }
}
