package com.rec.orch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rec.orch.model.ClientRegistry;
import com.rec.orch.model.RecOrchestratorClient;
import com.rec.orch.model.RecOrchestratorProcess;
import com.rec.orch.model.RecProcessData;
import com.rec.orch.model.RecommendApps;
import com.rec.orch.service.RabbitMessageService;
import com.rec.orch.service.RecOrchestratorService;

@RestController
public class RecOrchestratorController {
	
	@Autowired
	RecOrchestratorService recOrchService;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	RabbitMessageService rabbitMessageService;
	
	@PostMapping("getAllClientsWithRecommender")
	public ResponseEntity<ClientRegistry[]> getAllClientsWithRecommender() throws Exception{
	
		HashMap<Long,RecommendApps > hashMapRecApps = new HashMap<Long, RecommendApps>();
		HashMap<Long,List<RecommendApps>> hashMapRecOrchs = new HashMap<Long, List<RecommendApps>>();

		ClientRegistry[] clientRegistrys =  restTemplate.getForObject("http://manage-client-services/listClientsGet", ClientRegistry[].class);
		RecommendApps[] recApps = restTemplate.getForObject("http://recommender-registry-services/getAllRecommenderListGet", RecommendApps[].class);
		
		if(null != recApps && recApps.length > 0) {
			for(RecommendApps apps : recApps) {
				hashMapRecApps.put(apps.getRecId(), apps);
			}
		}
		
		List<RecOrchestratorClient> recOrchClients = recOrchService.getAllClientsWithRecommender();
		if(null != recOrchClients && recOrchClients.size() > 0) {
			for(RecOrchestratorClient client : recOrchClients) {
				
				List<RecommendApps> listRecommend = null;
				if(!hashMapRecOrchs.containsKey(client.getClientId())) {
					listRecommend = new ArrayList<RecommendApps>();
				}else {
					listRecommend= hashMapRecOrchs.get(client.getClientId());
				}
				listRecommend.add(hashMapRecApps.get(client.getRecId()));
				hashMapRecOrchs.put(client.getClientId(), listRecommend);
				
				
			}
		}
		
		
		for(ClientRegistry clientRegistry:clientRegistrys) {
			
			if(hashMapRecOrchs.containsKey(clientRegistry.getId())) {
				clientRegistry.setRecApps(hashMapRecOrchs.get(clientRegistry.getId()));
			}
		}
		
		return new ResponseEntity<ClientRegistry[]>(clientRegistrys, HttpStatus.OK);

	}
	
	@PostMapping("addRecommenderForClient")
	public ResponseEntity<RecOrchestratorClient> addRecommenderForClient(@RequestBody RecOrchestratorClient recOrchestratorClient) throws Exception{
		
		recOrchestratorClient = recOrchService.addRecommenderForClient(recOrchestratorClient);

		return new ResponseEntity<RecOrchestratorClient>(recOrchestratorClient, HttpStatus.OK);

	}
	
	@PostMapping("getProcessConfigDetails")
	public ResponseEntity<List<RecOrchestratorProcess>> getProcessConfigDetails(@RequestBody String req) throws Exception{
		
		 
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);

		Long recId =  Long.parseLong(String.valueOf(json.get("recId")));
		Long clientId =  Long.parseLong(String.valueOf(json.get("clientId")));

		List<RecOrchestratorProcess> recOrchestratorProcess = recOrchService.getProcessConfigDetails(recId, clientId);

		 List<RecProcessData> recProcessData = recOrchService.getProcessDetails(recId);
		 
		 for(RecOrchestratorProcess processORch : recOrchestratorProcess) {
			 
			 for(RecProcessData process :recProcessData ) {
				 
				 if(processORch.getProcessId().equals(process.getRecProcessId())) {
					 processORch.setProcessName(process.getProcessName());
					 processORch.setProcessUrl(process.getApiUrl());
				 }
			 }
		 }
		    
		return new ResponseEntity<List<RecOrchestratorProcess>>(recOrchestratorProcess, HttpStatus.OK);

	}
	
	
	@PostMapping("saveProcessOrch")
	public ResponseEntity<RecOrchestratorProcess> saveProcessOrch(@RequestBody RecOrchestratorProcess orchestratorProcess) throws Exception{
		
		orchestratorProcess = recOrchService.saveProcessOrch(orchestratorProcess);
		return new ResponseEntity<RecOrchestratorProcess>(orchestratorProcess, HttpStatus.OK);

	}

	@PostMapping("deleteProcessOrch")
	public ResponseEntity<String> deleteProcessOrch(@RequestBody String req) throws Exception{
		
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		
		Long deleteProcessOrch =  Long.parseLong(String.valueOf(json.get("orchPrcId")));
		
		recOrchService.deleteProcessOrch(deleteProcessOrch);
		
		return new ResponseEntity<String>(req, HttpStatus.OK);

	}
	
	
	@PostMapping("updateProcessStatus")
	public ResponseEntity<String> updateProcessStatus(@RequestBody String request) throws Exception{
		

		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		Long processOrchId =  Long.parseLong(String.valueOf(json.get("processOrchId")));
		String status =  String.valueOf(json.get("status"));
		recOrchService.updateProcessStatus(processOrchId, status);
		
		return new ResponseEntity<String>(request, HttpStatus.OK);

	}
	
	@PostMapping("updateProcessStatusDone")
	public ResponseEntity<String> updateProcessStatusDone(@RequestBody String request) throws Exception{
		
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		Long processOrchId =  Long.parseLong(String.valueOf(json.get("processOrchId")));
		String status =  String.valueOf(json.get("status"));
		String output =  String.valueOf(json.get("output"));
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		recOrchService.updateProcessStatus(processOrchId, status, output, timestamp);
		
		return new ResponseEntity<String>(request, HttpStatus.OK);

	}
	
	@PostMapping("getRecommenderListClient")
	public ResponseEntity<RecommendApps[]> getRecommenderListClient(@RequestBody String req) throws Exception{
	
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		Long clientId =  Long.parseLong(String.valueOf(json.get("clientId")));
		
		Long[] recOrchClients = recOrchService.getRecommenderListClient(clientId);

		RecommendApps[] recApps = restTemplate.postForObject("http://recommender-registry-services/getRecommenderListClient", recOrchClients,RecommendApps[].class);
		
		return new ResponseEntity<RecommendApps[]>(recApps, HttpStatus.OK);

	}
	
	
	@PostMapping("/publishQueue")
	public ResponseEntity<String> publishQueue(@RequestBody String request) throws Exception{
	
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);

		Long recId =  Long.parseLong(String.valueOf(json.get("recId")));
		Long clientId =  Long.parseLong(String.valueOf(json.get("clientId")));

		rabbitMessageService.sendRecommendarMessage(recId, clientId);
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		recOrchService.updateRecommenderOrchClient(recId,clientId, "W", timestamp, null);

		
		
		return new ResponseEntity<String>("", HttpStatus.OK);

	}
	
}
