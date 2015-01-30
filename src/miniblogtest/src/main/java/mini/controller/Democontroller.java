package mini.controller;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mini.jsonreturn.format.genericformat;
import mini.model.users;
import mini.resteasy.form.signupform;
import mini.service.User_service;

import org.jboss.resteasy.annotations.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class Democontroller {
	
	@Autowired
	private User_service user_service;

    @POST
	@Path("adduser")
    @Produces(MediaType.APPLICATION_JSON)
	public genericformat Create_user(@Form signupform data){
    	genericformat resp = new genericformat();
    	if(user_service.Validate_user(data)){
    		if(!user_service.Check_User_Exist(data.getUsername())){
    			if(!user_service.Check_Email_Exist(data.getEmail())){
    				users user = new users();
    				user.setUsername(data.getUsername());
    				user.setPassword(data.getPassword());
    				user.setFirstname(data.getFirstname());
    				user.setLastname(data.getLastname());
    				user.setEmail(data.getEmail());
    				user.setCreate_at(Calendar.getInstance().getTime());
    				user.setModified_at(Calendar.getInstance().getTime());
    				
    				user = user_service.Insert_user(user);
    				if(user!=null){
    					resp.data=user;
        				resp.meta.code=200;
        				resp.meta.description="User account was created successfully.";
        				resp.meta.message="Account created success!";
        				}
    				else{
                		resp.meta.code=9001;
                		resp.meta.description="System DAO error";
    				}
    			}else{
            		resp.meta.code=1003;
            		resp.meta.description="Email already exist";
    			}
    		}else{
        		resp.meta.code=1002;
        		resp.meta.description="Username already exist";
    		}
    	}
    	else{
    		resp.meta.code=1001;
    		resp.meta.description="Input validation failed.";
    	}
    	return resp;
	}

	@GET
	@Path("hello")
    public Response viewLike() {
	    return Response.status(200).build();
	}
}
