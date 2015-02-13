package mini.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
import mini.form.response.PostFull;
import mini.model.posts;
import mini.model.token;
import mini.model.users;
import mini.service.DataValidatorServiceInterface;
import mini.service.PostServiceInterface;
import mini.service.TokenServiceInterface;
import mini.service.UserServiceInterface;
import mini.systemvalue.SystemValue;
import mini.util.ErrorCodeUtil;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.plugins.validation.hibernate.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1/post")
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class PostController {

    @Autowired
    private DataValidatorServiceInterface data_validator;
    @Autowired
    private TokenServiceInterface token_service;
    @Autowired
    private PostServiceInterface post_service;
    @Autowired
    private UserServiceInterface user_service;

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createPost(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH)	 @HeaderParam("access_token") String access_token,
        @Form @Valid PostCreateForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        int post_id=post_service.createPost(data.set_post_data(token.getUser()));

        if(post_id==0){
            return Response.status(SystemValue.ERRORCODE_3001)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3001))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post create success",
                    post_id))
                .build();
    }

    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response editPost(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid PostEditForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        if(!post_service.checkPostOwn(data.id, token.getUser().getId())){
            return Response.status(SystemValue.ERRORCODE_3005)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005))
                .build();}

        if(!post_service.editPost(data)){
            return Response.status(SystemValue.ERRORCODE_3003)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3003))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post update success",
                    null))
                .build();
    }

    @PUT
    @Path("status")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response changePostStatus(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid PostEditForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        if(!post_service.checkPostOwn(data.id, token.getUser().getId())){
            return Response.status(SystemValue.ERRORCODE_3005)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005))
                .build();}

        if(!post_service.editStatusPost(data)){
            return Response.status(SystemValue.ERRORCODE_3002)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3002))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post update success",
                    null))
                .build();
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deletePost(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid PostEditForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        if(!post_service.checkPostOwn(data.id, token.getUser().getId())){
            return Response.status(SystemValue.ERRORCODE_3005)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005))
                .build();}

        if(!post_service.deletePost(data)){
            return Response.status(SystemValue.ERRORCODE_3004)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3004))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post delete success",
                    null))
                .build();
    }

    @GET
    @Path("getpost")
    public Response getPost(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @QueryParam("mode") String mode,
        @QueryParam("id")int id,
        @QueryParam("limit")int limit,
        @QueryParam("keysearch")String keysearch){

        users user;

      if(!data_validator.validatePostGetParameter(mode)){
       return Response.status(SystemValue.ERRORCODE_1001)
           .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_1001))
           .build();}
        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        switch(mode){

        case "all":
            return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200,
                    "Get all post",
                    post_service.getAllPost()))
                .build();

        case "current":
            user = token.getUser();

            if(user==null){
                return Response.status(SystemValue.ERRORCODE_2009)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2009))
                    .build();}

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "all post current user",
                        post_service.getAllPostByUserId(user.getId())))
                    .build();

        case "userid":
            user = user_service.getUserById(id);

            if(user==null){
                return Response.status(SystemValue.ERRORCODE_2009)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2009))
                    .build();}

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "all post of user by id",
                        post_service.getAllPostByUserId(user.getId())))
                    .build();

        case "postid":
            posts post = post_service.getPostByPostId(id);

            if(post==null){
                return Response.status(SystemValue.ERRORCODE_3005)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005))
                    .build();}

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "all post of user by id",
                        new PostFull(post)))
                    .build();

        case "top":

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "top post",
                        post_service.getTopPost(limit)))
                    .build();

        case "title":

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "search title",
                        post_service.searchPostByTitle(keysearch)))
                    .build();

        }
        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200,
                    "invalid",
                    null))
                .build();
    }
}
