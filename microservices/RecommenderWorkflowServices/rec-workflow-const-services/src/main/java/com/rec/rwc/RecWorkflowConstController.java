package com.rec.rwc;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rec.rwc.model.RecProcessData;
import com.rec.rwc.model.RecommendApps;
import com.rec.rwc.service.RecWorkflowService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecWorkflowConstController {
	
	@Autowired
	RecWorkflowService recWorkflowService;
	
	
	
	@PostMapping("/getAllProcessDetails")
	public ResponseEntity<List<RecProcessData>> getAllProcessDetails(@RequestBody String request) throws Exception{
	
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		Long recId =  Long.parseLong(String.valueOf(json.get("recId")));
		List<RecProcessData> recProcessData = recWorkflowService.getAllProcessDetails(recId);
		return new ResponseEntity<List<RecProcessData>>(recProcessData, HttpStatus.OK);

	}
	
	
	@PostMapping("/saveProcess")
	public ResponseEntity<RecProcessData> saveProcess(@RequestBody RecProcessData request) throws Exception{
	
		RecommendApps recommendApp = new RecommendApps();
		recommendApp.setRecId(request.getRecId());
		request.setRecommendApps(recommendApp);
		request = recWorkflowService.saveProcess(request);
		
		return new ResponseEntity<RecProcessData>(request, HttpStatus.OK);

	}
	
	@PostMapping("/editProcess")
	public ResponseEntity<RecProcessData> editProcess(@RequestBody RecProcessData request) throws Exception{
	
		RecommendApps recommendApp = new RecommendApps();
		recommendApp.setRecId(request.getRecId());
		request.setRecommendApps(recommendApp);
		request = recWorkflowService.editProcess(request);
		
		return new ResponseEntity<RecProcessData>(request, HttpStatus.OK);

	}
	
	@PostMapping("/getProcessDetails")
	public ResponseEntity<HashMap> getProcessDetails(@RequestBody String request) throws Exception{
	
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		
		HashMap map = new HashMap<String, Object>(); 

		Long processId =  Long.parseLong(String.valueOf(json.get("processId")));
		
		RecProcessData processData = recWorkflowService.getProcessDetails(processId);
		map.put("RecProcessData", processData);
		map.put("RecommendApps", processData.getRecommendApps());
		
		return new ResponseEntity<HashMap>(map, HttpStatus.OK);

	}
	
	@PostMapping("/deleteProcess")
	public ResponseEntity<RecProcessData> deleteProcess(@RequestBody RecProcessData request) throws Exception{
	
		recWorkflowService.deleteProcess(request.getRecProcessId());
		
		return new ResponseEntity<RecProcessData>(request, HttpStatus.OK);

	}
	
	@PostMapping("/updateProcessStatus")
	public ResponseEntity<String> updateProcessStatus(@RequestBody String request) throws Exception{
	
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		
		Long processId =  Long.parseLong(String.valueOf(json.get("processId")));
		String status =  String.valueOf(json.get("status"));

		recWorkflowService.updateProcessStatus(processId, status);
		
		return new ResponseEntity<String>(request, HttpStatus.OK);

	}

}
