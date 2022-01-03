package com.rec.rwc;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rec.rwc.model.RecommendApps;
import com.rec.rwc.service.RecRegistryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecRegistryController {
	
	@Autowired
	RecRegistryService recRegistryService;
	
	@PostMapping("/getAllRecommenderList")
	public ResponseEntity<List<RecommendApps>> getAllRecommenderList(@RequestBody String request) throws Exception{
		 
		List<RecommendApps> apps = recRegistryService.getAllRecommenderList();
		return new ResponseEntity<List<RecommendApps>>(apps, HttpStatus.OK);

	}
	
	@PostMapping("/getRecommenderListClient")
	public ResponseEntity<List<RecommendApps>> getRecommenderListClient(@RequestBody Long[] recIds) throws Exception{
		 
		List<RecommendApps> apps = recRegistryService.getRecommenderListClient(recIds);
		return new ResponseEntity<List<RecommendApps>>(apps, HttpStatus.OK);

	}
	
	@GetMapping("/getAllRecommenderListGet")
	public ResponseEntity<List<RecommendApps>> getAllRecommenderListGet() throws Exception{
		 
		List<RecommendApps> apps = recRegistryService.getAllRecommenderList();
		return new ResponseEntity<List<RecommendApps>>(apps, HttpStatus.OK);

	}
	
	@PostMapping("/addRecommenderDetails")
	public ResponseEntity<RecommendApps> addRecommenderDetails(@RequestBody RecommendApps recommendApps) throws Exception{
		 
		RecommendApps apps = recRegistryService.addRecommenderDetails(recommendApps);
		return new ResponseEntity<RecommendApps>(apps, HttpStatus.OK);
	}
	

	@PostMapping("/getRecommenderDetails")
	public ResponseEntity<RecommendApps> getRecommenderDetails(@RequestBody String request) throws Exception{
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		Long recId =  Long.parseLong(String.valueOf(json.get("recId")));
		RecommendApps apps = recRegistryService.getRecommenderDetails(recId);
		
		return new ResponseEntity<RecommendApps>(apps, HttpStatus.OK);

	}
	
	@PostMapping("/updateRecommenderDetails")
	public ResponseEntity<RecommendApps> updateRecommenderDetails(@RequestBody RecommendApps recommendApps) throws Exception{
		 
		recRegistryService.updateRecommenderDetails(recommendApps);
		return new ResponseEntity<RecommendApps>(recommendApps, HttpStatus.OK);
	}

	@PostMapping("/getPublishedRecommenderList")
	public ResponseEntity<List<RecommendApps>> getPublishedRecommenderList(@RequestBody String request) throws Exception{
		 
		List<RecommendApps> apps = recRegistryService.getPublishedRecommenderList();
		return new ResponseEntity<List<RecommendApps>>(apps, HttpStatus.OK);

	}

}
