package mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Democontroller {
	@RequestMapping("/test")
	@ResponseBody
	public String testdb(@RequestParam("data") String data){
		
		return null;
	}
}
