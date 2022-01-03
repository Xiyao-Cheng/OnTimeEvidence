package com.otr.web.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.otr.web.utility.RestClient;

@Controller
@RequestMapping("/webServices")
public class ServicesAPI {
	
	@RequestMapping(value = "callServices",method = RequestMethod.POST)
	public ResponseEntity<String>  callServices(@RequestBody String request) throws Exception{
		
		String output="";
		
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		String url =  String.valueOf(json.get("url"));
		JSONObject recInputParam = (JSONObject) json.get("recInputParam");
		
		output= RestClient.callRestAPIForRecommender(recInputParam, url, HttpMethod.POST);
		
		return new ResponseEntity<String>(output, HttpStatus.OK);

		
	}
	
}
