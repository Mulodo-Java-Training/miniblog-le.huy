package mini.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mini.form.request.PostCreateForm;
import mini.form.request.PostEditForm;
import mini.model.Posts;
import mini.model.Token;
import mini.model.Users;
import mini.service.PostServiceInterface;
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
 */
@Controller
@Path("/v1/post")
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class PostController
{

    @Autowired
    private TokenServiceInterface token_service;

    @Autowired
    private PostServiceInterface post_service;

    @Autowired
    private UserServiceInterface user_service;

    /**
     * @param access_token
     * @param data
     * @return
     */
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createPost(
            @Pattern(regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE) @HeaderParam("access_token") String access_token,
            @Form @Valid PostCreateForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null) {
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008)).build();
        }

        int post_id = post_service.createPost(data.set_post_data(token.getUser()));

        if (post_id == 0) {
            return Response.status(SystemValue.ERRORCODE_3001)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3001)).build();
        }

        return Response
                .status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil
                        .set_code(SystemValue.CODE_200, "post create success", post_id)).build();
    }

    /**
     * @param access_token
     * @param data
     * @return
     */
    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response editPost(
            @Pattern(regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE) @HeaderParam("access_token") String access_token,
            @Form @Valid PostEditForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null) {
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008)).build();
        }

        if (!post_service.checkPostOwn(data.id, token.getUser().getId())) {
            return Response.status(SystemValue.ERRORCODE_3005)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005)).build();
        }

        if (!post_service.editPost(data)) {
            return Response.status(SystemValue.ERRORCODE_3003)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3003)).build();
        }

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "post update success", null))
                .build();
    }

    /**
     * @param access_token
     * @param data
     * @return
     */
    @PUT
    @Path("status")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response changePostStatus(
            @Pattern(regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE) @HeaderParam("access_token") String access_token,
            @Form @Valid PostEditForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null) {
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008)).build();
        }

        if (!post_service.checkPostOwn(data.id, token.getUser().getId())) {
            return Response.status(SystemValue.ERRORCODE_3005)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005)).build();
        }

        if (!post_service.editStatusPost(data)) {
            return Response.status(SystemValue.ERRORCODE_3002)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3002)).build();
        }

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "post update success", null))
                .build();
    }

    /**
     * @param access_token
     * @param data
     * @return
     */
    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deletePost(
            @Pattern(regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE) @HeaderParam("access_token") String access_token,
            @Form @Valid PostEditForm data)
    {

        Token token = token_service.checkAccessToken(access_token);

        if (token == null) {
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008)).build();
        }

        if (!post_service.checkPostOwn(data.id, token.getUser().getId())) {
            return Response.status(SystemValue.ERRORCODE_3005)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005)).build();
        }

        if (!post_service.deletePost(data)) {
            return Response.status(SystemValue.ERRORCODE_3004)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3004)).build();
        }

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "post delete success", null))
                .build();
    }

    /**
     * @param access_token
     * @param mode
     * @param id
     * @param limit
     * @param keysearch
     * @return
     */
    @GET
    @Path("getpost")
    public Response getPost(
            @Pattern(regexp = SystemValue.ACCESS_TOKEN_STRING_RANGE) @HeaderParam("access_token") String access_token,
            @QueryParam("mode") String mode,
            @QueryParam("id") int id,
            @QueryParam("limit") int limit,
            @Pattern(regexp = SystemValue.POST_TITLE_SEARCH_RANGE) @QueryParam("keysearch") String keysearch)
    {

        if (!SystemValue.POST_GET_MODE.contains(mode)) {
            return Response.status(SystemValue.ERRORCODE_1001)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_1001)).build();
        }

        Users user;
        Token token = token_service.checkAccessToken(access_token);

        if (token == null) {
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008)).build();
        }

        Response resp = Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "invalid", null)).build();
        if (mode == SystemValue.POST_GET_MODE_ALL) {
            resp = Response
                    .status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "Get all post",
                            post_service.getAllPost())).build();
        }
        if (mode == SystemValue.POST_GET_MODE_CURRENT) {
            user = token.getUser();

            if (user == null) {
                resp = Response.status(SystemValue.ERRORCODE_2009)
                        .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2009)).build();
            } else {
                resp = Response
                        .status(SystemValue.CODE_200)
                        .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                                "all post current user",
                                post_service.getAllPostByUserId(user.getId()))).build();
            }
        }
        if (mode == SystemValue.POST_GET_MODE_USERID) {
            user = user_service.getUserById(id);

            if (user == null) {
                resp = Response.status(SystemValue.ERRORCODE_2009)
                        .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2009)).build();
            } else {
                resp = Response
                        .status(SystemValue.CODE_200)
                        .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                                "all post of user by id",
                                post_service.getAllPostByUserId(user.getId()))).build();
            }
        }
        if (mode == SystemValue.POST_GET_MODE_POSTID) {
            Posts post = post_service.getPostByPostId(id);

            if (post == null) {
                resp = Response.status(SystemValue.ERRORCODE_3005)
                        .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005)).build();
            } else {
                resp = Response
                        .status(SystemValue.CODE_200)
                        .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
                                "all post of user by id", post)).build();
            }
        }
        if (mode == SystemValue.POST_GET_MODE_TOP) {

            resp = Response
                    .status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "top post",
                            post_service.getTopPost(limit))).build();
        }
        if (mode == SystemValue.POST_GET_MODE_TITLE) {

            resp = Response
                    .status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "search title",
                            post_service.searchPostByTitle(keysearch))).build();
        }
        return resp;
    }
}
