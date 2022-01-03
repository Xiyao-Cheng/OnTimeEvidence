package com.rec.cloud.template;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CloudTempRunModelController {

	
	@Autowired
	private Environment env;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String runModel(@RequestBody String req) throws Exception {
    	
        String cloudTemp = env.getProperty("cloud.template.service.url");
        //String result = restTemplate.postForObject( cloudTemp, req, String.class);
    	


    		String response = null;
    		ResponseEntity<String> result = null;
    		RestTemplate restTemplate = new RestTemplate();

    		HttpHeaders requestHeaders = new HttpHeaders();
    		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    		HttpEntity<String> entity = null;

		
			entity = new HttpEntity<String>(req, requestHeaders);
			result = restTemplate.exchange(cloudTemp, HttpMethod.POST, entity, String.class);

		

    		if (result.getStatusCode().equals(HttpStatus.OK)) {
    			response = result.getBody();
    		}

    
    	
        return response;
    }

}
