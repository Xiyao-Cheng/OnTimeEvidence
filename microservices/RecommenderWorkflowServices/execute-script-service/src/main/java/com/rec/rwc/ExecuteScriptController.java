package com.rec.rwc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecuteScriptController {

	@PostMapping("/executeScript")
	@ResponseBody
    public String executeScript(@RequestBody String request) throws Exception {
    	JSONParser parser = new JSONParser();

		JSONObject json=(JSONObject) parser.parse(request);
		String command = String.valueOf(json.get("cmd"));
		String result = executeScriptProcess(command);
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
