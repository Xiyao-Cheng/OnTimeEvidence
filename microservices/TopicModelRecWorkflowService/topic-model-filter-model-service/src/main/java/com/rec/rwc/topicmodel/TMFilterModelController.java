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
public class TMFilterModelController {

	@Autowired
	private Environment env;
	
	@Autowired
    RestTemplate restTemplate;
	
    @RequestMapping(value = "/listTopics", method = RequestMethod.POST)
    @ResponseBody
    public String listTopics(@RequestBody String req) throws Exception {
    	
        JSONObject executeStatus = new JSONObject();
    	String path = env.getProperty("python.topiclist.path");
        executeStatus.put("cmd", "python " + path);
    	String executeStatusStr = executeStatus.toJSONString();
        String result = restTemplate.postForObject( "http://execute-script-service/executeScript", executeStatusStr, String.class);

        result = result.replace("'", "\"");
        return result;
    }

    
    @RequestMapping(value = "/filterDocs", method = RequestMethod.POST)
    @ResponseBody
    public String filterDocs(@RequestBody String req) throws Exception {

    	JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		String topicSelected = String.valueOf(json.get("topicSelected"));
		String levelSelected = String.valueOf(json.get("levelSelected"));

        JSONObject executeStatus = new JSONObject();
    	String path = env.getProperty("python.filterdocs.path");
        executeStatus.put("cmd", "python " + path + " "+topicSelected + " " + levelSelected);
    	String executeStatusStr = executeStatus.toJSONString();
        String result = restTemplate.postForObject( "http://execute-script-service/executeScript", executeStatusStr, String.class);

        result = result.replace("'", "\"");
        return result;
    }

}
