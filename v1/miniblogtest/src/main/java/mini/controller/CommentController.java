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

import mini.form.request.CommentCreateForm;
import mini.form.request.CommentEditForm;
import mini.form.response.CommentFull;
import mini.model.comments;
import mini.model.posts;
import mini.model.token;
import mini.model.users;
import mini.service.CommentServiceInterface;
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
@Path("/v1/comment")
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class CommentController {

    @Autowired
    private DataValidatorServiceInterface data_validator;
    @Autowired
    private TokenServiceInterface token_service;
    @Autowired
    private PostServiceInterface post_service;
    @Autowired
    private CommentServiceInterface comment_service;
    @Autowired
    private UserServiceInterface user_service;

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createComment(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid CommentCreateForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        posts post = post_service.getPostByPostId(data.postid);

        if(post==null){
            return Response.status(SystemValue.ERRORCODE_3005)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_3005))
                .build();}

        int comment_id=comment_service.createComment(data.set_comment_data(token.getUser(), post));

        if(comment_id==0){
            return Response.status(SystemValue.ERRORCODE_4001)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4001))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "comment create success",
                    comment_id))
                .build();
    }

    @PUT
    @Path("edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response editComment(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid CommentEditForm data){

    token token = token_service.checkAccessToken(access_token);

    if(token==null){
        return Response.status(SystemValue.ERRORCODE_2008)
            .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
            .build();}

    if(!comment_service.checkCommentOwn(data.id, token.getUser().getId())){
        return Response.status(SystemValue.ERRORCODE_4004)
            .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4004))
            .build();}

    if(!comment_service.editComment(data)){
        return Response.status(SystemValue.ERRORCODE_4002)
            .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4002))
            .build();}
    
    return Response.status(SystemValue.CODE_200)
            .entity(ErrorCodeUtil.set_code(
                SystemValue.CODE_200, 
                "post update success",
                null))
            .build();
    }

    @POST
    @Path("del")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteComment(
    		@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
        @Form @Valid CommentEditForm data){

        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                .build();}

        if(!comment_service.checkCommentOwn(data.id, token.getUser().getId())){
            return Response.status(SystemValue.ERRORCODE_4004)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4004))
                .build();}

        if(!comment_service.deleteComment(data)){
            return Response.status(SystemValue.ERRORCODE_4003)
                .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4003))
                .build();}

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post delete success",
                    null))
                .build();
    }

    @GET
    @Path("get")
    public Response getComment(
        @HeaderParam("access_token") String access_token,
        @QueryParam("mode") String mode,
        @QueryParam("id")int id){

        users user;

      if(!data_validator.validateCommentGetParameter(mode)){
       return Response.status(SystemValue.ERRORCODE_1001)
           .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_1001))
           .build();}
        token token = token_service.checkAccessToken(access_token);

        if(token==null){
            return Response.status(SystemValue.ERRORCODE_2008)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2008))
                    .build();}

        switch(mode){

        case "id":
            comments comment = comment_service.getCommentById(id);

            if(comment==null){
                return Response.status(SystemValue.ERRORCODE_4004)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_4004))
                    .build();}

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "get comment by id",
                        new CommentFull(comment)))
                    .build();

        case "current":
            user = user_service.getUserById(token.getUser().getId());

            if(user==null){
                return Response.status(SystemValue.ERRORCODE_2009)
                    .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_2009))
                    .build();}

            return Response.status(SystemValue.CODE_200)
                    .entity(ErrorCodeUtil.set_code(
                        SystemValue.CODE_200,
                        "get comment by current user",
                        comment_service.getAllCommentByUserId(user.getId())))
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
                        "get comment by user id",
                        comment_service.getAllCommentByPostId(post.getId())))
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
                        "get comment by user id",
                        comment_service.getAllCommentByUserId(user.getId())))
                    .build();
        }

        return Response.status(SystemValue.CODE_200)
                .entity(ErrorCodeUtil.set_code(
                    SystemValue.CODE_200, 
                    "post get success",
                    null))
                .build();
    }
}
