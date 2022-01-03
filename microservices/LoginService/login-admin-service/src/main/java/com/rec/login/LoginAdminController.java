package com.rec.login;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rec.login.model.User;
import com.rec.login.service.LoginService;

@RestController
public class LoginAdminController {

	@Autowired
	LoginService loginService;
	
    @RequestMapping(value = "/createRecWorkflowConst", method = RequestMethod.GET)
    @ResponseBody
    public String createRecWorkflowConst() {
        return "createRecWorkflowConst";
    }

  
    @PostMapping("/getUserDetails")
	public ResponseEntity<User> getUserDetails(@RequestBody User user){
		 
		user.setRole("STUDENT");

		User user_from = loginService.getUserDetails(user.getUserName().toLowerCase(), user.getGithubId());
		
		if(null == user_from) {
			user_from = loginService.insertUserDetails(user);
		}
		
		return new ResponseEntity<User>(user_from, HttpStatus.OK);


	}
	
	@PostMapping("/addUserDetails")
	public ResponseEntity<User> addRecommenderDetails(@RequestBody User user) throws Exception{
		User user_ = loginService.insertUserDetails(user);
		return new ResponseEntity<User>(user_, HttpStatus.OK);
	}
	
	
	@PostMapping("/authenticateUser")
	public ResponseEntity<String> getRecommenderDetails(@RequestBody String request) throws Exception{

		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		String userName =  String.valueOf(String.valueOf(json.get("userName")));
		String password =  String.valueOf(String.valueOf(json.get("password")));
		
		User user = loginService.authenticateUser(userName, password);
		
		if(null != user)
		{
			return new ResponseEntity<String>("Authentication Successful !!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Authentication Failed !!", HttpStatus.NOT_FOUND);
		}
		


	}

}
