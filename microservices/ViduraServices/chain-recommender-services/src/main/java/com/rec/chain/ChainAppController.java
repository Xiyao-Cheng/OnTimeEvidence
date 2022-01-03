package com.rec.chain;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rec.chain.model.ChainApp;
import com.rec.chain.service.ChainAppService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChainAppController {
	
	@Autowired
	ChainAppService chainAppService;
	
	@PostMapping("/getRecommendationSuggestion")
	public ResponseEntity<ChainApp> getRecommendationSuggestion(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		String intentId = String.valueOf(json.get("intentId")).toUpperCase();
		String clientId = String.valueOf(json.get("clientId")).toUpperCase();
		String recKey = String.valueOf(json.get("recKey")).toUpperCase();
		Long userProfile = Long.parseLong(String.valueOf(json.get("userProfile")));
		
		ChainApp apps = chainAppService.getRecommendationSuggestion(intentId, clientId, recKey, userProfile);

		return new ResponseEntity<ChainApp>(apps, HttpStatus.OK);

	}
	


}
