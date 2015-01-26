package mini.controller;

import java.util.Map;

import mini.model.users;
import mini.service.User_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@Path("/usercontroller")
//@Produces(MediaType.APPLICATION_JSON)
public class Democontroller {
	
	@Autowired
	private User_service user_service;
	
//	@Path("/signup")
//    @GET
    @RequestMapping("/createuser")
	public String Create_user(@ModelAttribute users user,@RequestParam String action){
		switch(action.toLowerCase()){
		case "add":
			if(user_service.Validate_user(user)){
				
			}
			else
		break;
		}
		
		return "createuser";
	}
	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map){
		users user = new users();
		map.put("userreturn", user);
		return "createuser";
	}
}
