package com.chatbot;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.model.UserProfile;
import com.chatbot.service.ChatBotService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserProfileController {
	

	
	@Autowired
	ChatBotService chatBotService; 
	
	@PostMapping("/checkForUser")
	public ResponseEntity<UserProfile> checkForUser(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		String userName =  String.valueOf(json.get("userName"));
		String domain =  String.valueOf(json.get("domain"));
		
		UserProfile userProfile = chatBotService.getUserDtls(userName, domain);
		if(null == userProfile) {
			userProfile = new UserProfile();
			userProfile.setUserName(userName.toUpperCase());
			userProfile.setUsecase(domain.toUpperCase());
			userProfile = chatBotService.insertUserDtls(userProfile);
		}
		
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);

	}
	
	@PostMapping("/submitSurvay")
	public ResponseEntity<UserProfile> submitSurvay(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		String userId =  String.valueOf(json.get("userId"));
		String response = String.valueOf(json.get("response"));
		
		JSONObject responseJSON =(JSONObject) parser.parse(response);
		JSONObject neuro = (JSONObject) responseJSON.get("neuro");
		Double neuroPoints = 0.0;
		for(int i=1; i<=7; i++) {
			Double points = Double.valueOf(String.valueOf(neuro.get("q"+i)));
			neuroPoints = neuroPoints + points ;
		}

		Double perNeuro = (neuroPoints/56)*100; 
		
		JSONObject hpc = (JSONObject) responseJSON.get("hpc");
		Double hpcPoints = 0.0;
		for(int i=1; i<=5; i++) {
			Double points = Double.valueOf(String.valueOf(hpc.get("q"+i)));
			hpcPoints = hpcPoints + points ;
		}
		
		String userQuadrant="";

		Double perHpc = (hpcPoints/40)*100; 
		
		if(perNeuro >= 50 && perHpc >= 50) {
			userQuadrant = "4"; //expert in both domian
		}else if(perNeuro < 50 && perHpc >= 50) {
			userQuadrant = "3"; //expert in hpc domian
		}else if(perNeuro >= 50 && perHpc < 50) {
			userQuadrant = "2"; //expert in neuro domian
		}else {
			userQuadrant = "1"; //novice
		}
		
		UserProfile userProfile = chatBotService.getUserProfile(Long.parseLong(userId));
		userProfile.setUserResponse(response);
		userProfile.setUserQuadrant(userQuadrant);
		userProfile.setUsecase("NEURO");
		userProfile = chatBotService.insertUserDtls(userProfile);
		
		
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);

	}
	
	@PostMapping("/submitSurvayBio")
	public ResponseEntity<UserProfile> submitSurvayBio(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		String userId =  String.valueOf(json.get("userId"));
		String response = String.valueOf(json.get("response"));
		
		JSONObject responseJSON =(JSONObject) parser.parse(response);
		JSONObject neuro = (JSONObject) responseJSON.get("bio");
		Double neuroPoints = 0.0;
		for(int i=1; i<=5; i++) {
			Double points = Double.valueOf(String.valueOf(neuro.get("q"+i)));
			neuroPoints = neuroPoints + points ;
		}

		Double perNeuro = (neuroPoints/40)*100; 
		
		JSONObject hpc = (JSONObject) responseJSON.get("hpc");
		Double hpcPoints = 0.0;
		for(int i=1; i<=5; i++) {
			Double points = Double.valueOf(String.valueOf(hpc.get("q"+i)));
			hpcPoints = hpcPoints + points ;
		}
		
		String userQuadrant="";

		Double perHpc = (hpcPoints/40)*100; 
		
		if(perNeuro >= 50 && perHpc >= 50) {
			userQuadrant = "4"; //expert in both domian
		}else if(perNeuro < 50 && perHpc >= 50) {
			userQuadrant = "3"; //expert in hpc domian
		}else if(perNeuro >= 50 && perHpc < 50) {
			userQuadrant = "2"; //expert in bioinfo domian
		}else {
			userQuadrant = "1"; //novice
		}
		
		UserProfile userProfile = chatBotService.getUserProfile(Long.parseLong(userId));
		userProfile.setUserResponse(response);
		userProfile.setUserQuadrant(userQuadrant);
		userProfile.setUsecase("BIO");
		userProfile = chatBotService.insertUserDtls(userProfile);
		
		
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);

	}

	
}
