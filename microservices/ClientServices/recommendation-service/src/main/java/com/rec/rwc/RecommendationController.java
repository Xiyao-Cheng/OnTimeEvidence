package com.rec.rwc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rec.rwc.model.RecommendApps;

@RestController
public class RecommendationController {

	@Autowired
    RestTemplate restTemplate;
	
	@PostMapping("/getRecommendation")
	public ResponseEntity<String> getRecommendation(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		Long recId =  Long.parseLong(String.valueOf(json.get("recId")));
		String inputs =  String.valueOf(json.get("recInputParam"));
		String domain =  String.valueOf(json.get("domain"));

		JSONObject reqUpdateStatus = new JSONObject();
		reqUpdateStatus.put("recId", recId);
    	String reqUpdateStatusStr = reqUpdateStatus.toJSONString();
    	RecommendApps recApps = restTemplate.postForObject( "http://recommender-registry-services/getRecommenderDetails", reqUpdateStatusStr, RecommendApps.class);
    	
    	String url = "http://"+recApps.getApiUrl();
    	String output = "";

    	if(recId == 21) {
    		output = restTemplate.postForObject(url, request, String.class);
    	}else {
    		output = restTemplate.postForObject(url, inputs, String.class);
    	}
        	
    	//parser = new JSONParser();
    	//JSONArray outputjson=(JSONArray) parser.parse(output.toString());

		return new ResponseEntity<String>(output, HttpStatus.OK);

	}
	
	
	@PostMapping("/getRecommenderListClient")
	public ResponseEntity<RecommendApps[]> getRecommenderListClient(@RequestBody String request) throws Exception{
		
    	RecommendApps[] recApps = restTemplate.postForObject( "http://rec-orch-process-services/getRecommenderListClient", request, RecommendApps[].class);
  

		return new ResponseEntity<RecommendApps[]>(recApps, HttpStatus.OK);

	}
	
	

}
