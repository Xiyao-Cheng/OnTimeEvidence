package com.rec.rwc.topicmodel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TMDataCollectionController {

	@Autowired
	private Environment env;
	
	@Autowired
    RestTemplate restTemplate;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String dataCollection(@RequestBody String req) throws Exception {
    	
    	JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		Long processOrchId =  Long.parseLong(String.valueOf(json.get("processOrchId")));
		String inputConfigs = String.valueOf(json.get("inputConfig"));
		JSONObject jsonInputConfigs = (JSONObject) parser.parse(inputConfigs);
		String domainName = String.valueOf(jsonInputConfigs.get("domain"));
		
		JSONObject reqUpdateStatus = new JSONObject();
		reqUpdateStatus.put("processOrchId", processOrchId);
		reqUpdateStatus.put("status", "W");
    	String reqUpdateStatusStr = reqUpdateStatus.toJSONString();
        String result = restTemplate.postForObject( "http://rec-orch-process-services/updateProcessStatus", reqUpdateStatusStr, String.class);
    	
        JSONObject executeStatus = new JSONObject();
        String path = env.getProperty("pythonscript.path");
        executeStatus.put("cmd", "python " + path + " --domain "+domainName);
    	String executeStatusStr = executeStatus.toJSONString();
    	result = "dataCollection";
        //result = restTemplate.postForObject( "http://execute-script-service/executeScript", executeStatusStr, String.class);

        reqUpdateStatus = new JSONObject();
		reqUpdateStatus.put("processOrchId", processOrchId);
		reqUpdateStatus.put("status", "Y");
		reqUpdateStatus.put("output", result);
    	reqUpdateStatusStr = reqUpdateStatus.toJSONString();
        result = restTemplate.postForObject( "http://rec-orch-process-services/updateProcessStatusDone", reqUpdateStatusStr, String.class);
    	
		
        return "dataCollection";
    }

}
