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
public class TMRunModelController {

	@Autowired
	private Environment env;
	
	@Autowired
    RestTemplate restTemplate;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String runModel(@RequestBody String req) throws Exception {
    	
        JSONObject executeStatus = new JSONObject();
        
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		String inputs =  String.valueOf(json.get("recInputParam"));
		String domain =  String.valueOf(json.get("domain"));
		
		JSONParser iparser = new JSONParser();
		JSONObject ijson=(JSONObject) iparser.parse(inputs);
		String text = String.valueOf(ijson.get("text"));
				
		
    	String path = env.getProperty("pythonscript.path");
        executeStatus.put("cmd", "python " + path + " \""+ text + "\" " + domain);
    	String executeStatusStr = executeStatus.toJSONString();
        String result = restTemplate.postForObject( "http://execute-script-service/executeScript", executeStatusStr, String.class);

        result = result.replace("'", "\"");
        return result;
    }

}
