package mini.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
import mini.form.response.TokenShort;
import mini.form.response.UserFull;
import mini.model.token;
import mini.model.users;
import mini.service.DataValidatorServiceInterface;
import mini.service.TokenServiceInterface;
import mini.service.UserServiceInterface;
import mini.systemvalue.SystemValue;
import mini.util.ErrorCodeUtil;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.plugins.validation.hibernate.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1/user")
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class UserController {

	@Autowired
	private UserServiceInterface user_service;
	@Autowired
	private DataValidatorServiceInterface data_validator;
	@Autowired
	private TokenServiceInterface token_service;

	@POST
	@Path("adduser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(@Form @Valid UserSignupForm data) {

		if (user_service.checkUserExist(data.username)) {
			return Response
					.status(SystemValue.ERRORCODE_2001)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2001))
					.build();
		}

		if (user_service.checkEmailExist(data.email)) {
			return Response
					.status(SystemValue.ERRORCODE_2002)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2002))
					.build();
		}

		users user = data.set_sign_up_data();

		if (!user_service.insertUser(user)) {
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

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response loginUser(@Form @Valid UserLoginForm data) {

		String access_token_key = user_service.loginUser(data);

		if (access_token_key == null) {
			return Response
					.status(SystemValue.ERRORCODE_2004)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2004))
					.build();
		}

		TokenShort obj = new TokenShort();
		obj.access_token = access_token_key;

		return Response
				.status(SystemValue.CODE_200)
				.entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
						"Login successful!", obj)).build();
	}

	@POST
	@Path("logout")
	public Response logoutUser(
			@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token) {

		token token = token_service.checkAccessToken(access_token);

		if (token == null) {
			return Response
					.status(SystemValue.ERRORCODE_2008)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2008))
					.build();
		}

		if (!user_service.logoutUser(token)) {
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

	@PUT
	@Path("user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateUser(
			@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
			@Form @Valid UserUpdateForm data) {

		// if(!data_validator.validateAccessToken(access_token)||
		// !data_validator.validateUserUpdateData(data)){
		// return Response.status(SystemValue.ERRORCODE_1001)
		// .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_1001))
		// .build();}

		token token = token_service.checkAccessToken(access_token);

		if (token == null) {
			return Response
					.status(SystemValue.ERRORCODE_2008)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2008))
					.build();
		}

		if (!user_service.updateUserInfo(data, token.getUser().getId())) {
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

	@GET
	@Path("user")
	public Response getUserInfo(
			@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
			@QueryParam("mode") String mode, @QueryParam("id") int id) {

		users user;

		// if(!data_validator.validateAccessToken(access_token)||
		// !data_validator.validateUserGetInfoParameter(mode)){
		// return Response.status(SystemValue.ERRORCODE_1001)
		// .entity(ErrorCodeUtil.set_error_code(SystemValue.ERRORCODE_1001))
		// .build();}
		if (!data_validator.validateUserGetInfoParameter(mode)) {
			return Response
					.status(SystemValue.ERRORCODE_1001)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_1001))
					.build();
		}
		token token = token_service.checkAccessToken(access_token);

		if (token == null) {
			return Response
					.status(SystemValue.ERRORCODE_2008)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2008))
					.build();
		}

		switch (mode) {

		case "current":
			user = token.getUser();

			if (user == null) {
				return Response
						.status(SystemValue.ERRORCODE_2009)
						.entity(ErrorCodeUtil
								.set_error_code(SystemValue.ERRORCODE_2009))
						.build();
			}

			return Response
					.status(SystemValue.CODE_200)
					.entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
							"Info of current user", new UserFull(user)))
					.build();

		case "id":
			user = user_service.getUserById(id);

			if (user == null) {
				return Response
						.status(SystemValue.ERRORCODE_2009)
						.entity(ErrorCodeUtil
								.set_error_code(SystemValue.ERRORCODE_2009))
						.build();
			}

			return Response
					.status(SystemValue.CODE_200)
					.entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
							"Info of user by id", new UserFull(user))).build();

		}
		return Response.status(SystemValue.CODE_200)
				.entity(ErrorCodeUtil.set_code(SystemValue.CODE_200, "", null))
				.build();
	}

	@PUT
	@Path("pass")
	@Consumes("application/x-www-form-urlencoded")
	public Response changeUserPassword(
			@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
			@Form @Valid UserChangePasswordForm data) {

//		if (!data_validator.validateAccessToken(access_token)
//				|| !data_validator.validateChangePasswordData(data)) {
//			return Response
//					.status(SystemValue.ERRORCODE_1001)
//					.entity(ErrorCodeUtil
//							.set_error_code(SystemValue.ERRORCODE_1001))
//					.build();
//		}

		token token = token_service.checkAccessToken(access_token);

		if (token == null) {
			return Response
					.status(SystemValue.ERRORCODE_2008)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2008))
					.build();
		}

		String access_token_key = user_service.changePassword(data, token
				.getUser().getId());

		if (null == access_token_key) {
			return Response
					.status(SystemValue.ERRORCODE_2007)
					.entity(ErrorCodeUtil
							.set_error_code(SystemValue.ERRORCODE_2007))
					.build();
		}

		TokenShort obj = new TokenShort();
		obj.access_token = access_token_key;

		return Response
				.status(SystemValue.CODE_200)
				.entity(ErrorCodeUtil.set_code(SystemValue.CODE_200,
						"Password Change successful!", obj)).build();
	}

	@GET
	@Path("search/{query}")
	public Response searchUserByName(
			@Size(min = SystemValue.ACCESS_TOKEN_LENGTH, max = SystemValue.ACCESS_TOKEN_LENGTH) @HeaderParam("access_token") String access_token,
			@PathParam("query") String query) {

//		if (!data_validator.validateAccessToken(access_token)
//				|| !data_validator.validateSearchQuery(query)) {
//			return Response
//					.status(SystemValue.ERRORCODE_1001)
//					.entity(ErrorCodeUtil
//							.set_error_code(SystemValue.ERRORCODE_1001))
//					.build();
//		}
		if (!data_validator.validateSearchQuery(query)) {
	return Response
			.status(SystemValue.ERRORCODE_1001)
			.entity(ErrorCodeUtil
					.set_error_code(SystemValue.ERRORCODE_1001))
			.build();
}
		token token = token_service.checkAccessToken(access_token);

		if (token == null) {
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
