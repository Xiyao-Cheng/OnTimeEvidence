package com.web.cyneuro;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {
	
	@Autowired
	private Environment env;
	
	@PostMapping("/executeScriptListTopic")
	@ResponseBody
    public String executeScript(@RequestBody String request) throws Exception {
    	JSONParser parser = new JSONParser();

		JSONObject json=(JSONObject) parser.parse(request);
    	String command = env.getProperty("python.topiclist.command");
		String result = executeScriptProcess(command);
		result = result.replace("'", "\"");
        return result;
    }
    
	@PostMapping("/executeScriptFilterDocs")
	@ResponseBody
    public String executeScriptFilterDocs(@RequestBody String request) throws Exception {

    	JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(request);
		String topicSelected = String.valueOf(json.get("topicSelected"));
		String levelSelected = String.valueOf(json.get("levelSelected"));
		
    	String command = env.getProperty("python.filterdocs.command");
		String result = executeScriptProcess(command + " "+topicSelected + " " + levelSelected);
		result = result.replace("'", "\"");
        return result;
    }
    
    
    public String executeScriptProcess(String command) throws Exception {

    	
		BufferedReader reader = null;
		InputStreamReader in=null;
		String finalOutput = "";

		try {
			Process process = Runtime.getRuntime().exec(command);
			in = new InputStreamReader(process.getInputStream());

			reader = new BufferedReader(in);
			String output = "";
			while ((output = reader.readLine()) != null) {
				finalOutput = finalOutput + output;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			
			if(null != reader )
				reader.close();
			if(null != in)
				in.close();
		
		}
		
		return finalOutput;

	}
}
