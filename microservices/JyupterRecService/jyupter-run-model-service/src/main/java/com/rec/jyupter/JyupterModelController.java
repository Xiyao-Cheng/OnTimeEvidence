package com.rec.jyupter;

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
public class JyupterModelController {

	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String runModel(@RequestBody String req) throws Exception {
    	
        JSONObject executeStatus = new JSONObject();
        
    	JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		String text = String.valueOf(json.get("text"));
		
    	String path = env.getProperty("pythonscript.path");
        executeStatus.put("cmd", "python " + path + " \""+ text + "\"");
    	String executeStatusStr = executeStatus.toJSONString();
        String result = restTemplate.postForObject( "http://execute-script-service/executeScript", executeStatusStr, String.class);

        result = result.replace("'", "\"");
        return result;
    }

}
