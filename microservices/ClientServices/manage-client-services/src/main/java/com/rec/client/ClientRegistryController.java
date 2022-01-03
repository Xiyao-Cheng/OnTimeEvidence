package com.rec.client;

import java.security.MessageDigest;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
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

import com.rec.client.model.ClientRegistry;
import com.rec.client.service.ClientRegistryService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientRegistryController {
	
	@Autowired
	ClientRegistryService clientRegistryService;
	
	@PostMapping("/addClient")
	public ResponseEntity<ClientRegistry> addClient(@RequestBody ClientRegistry clientRegistry) throws Exception{
	
		String clientName = clientRegistry.getClientName();
		clientName = clientName.replace(' ', '-');
	    String randomString = RandomStringUtils.randomAlphanumeric(10);
	    String clientKey = clientName.substring(0,4) + "-" + randomString;
		clientRegistry.setClientKey(clientKey.toLowerCase());
		
		byte[] bytesOfPassword = clientRegistry.getPassword().getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] passwordDigst = md.digest(bytesOfPassword);

		clientRegistry.setPassword(passwordDigst.toString());
		clientRegistry = clientRegistryService.addClient(clientRegistry);
		
		return new ResponseEntity<ClientRegistry>(clientRegistry, HttpStatus.OK);

	}
	
	@PostMapping("/listClients")
	public ResponseEntity<List<ClientRegistry>> listClients() throws Exception{
	
		List<ClientRegistry> clientRegistry = clientRegistryService.listClients();
		
		return new ResponseEntity<List<ClientRegistry>>(clientRegistry, HttpStatus.OK);

	}
	
	@GetMapping("/listClientsGet")
	public ResponseEntity<List<ClientRegistry>> listClientsGet() throws Exception{
	
		List<ClientRegistry> clientRegistry = clientRegistryService.listClients();
		
		return new ResponseEntity<List<ClientRegistry>>(clientRegistry, HttpStatus.OK);

	}
	
	@PostMapping("/deleteClient")
	public ResponseEntity<String> deleteClient(@RequestBody String req) throws Exception{
		
		JSONParser parser = new JSONParser();
		JSONObject json=(JSONObject) parser.parse(req);
		Long id =  Long.parseLong(String.valueOf(json.get("id")));
		
		clientRegistryService.deleteClient(id);
		
		return new ResponseEntity<String>(req, HttpStatus.OK);

	}
	
	
	
}
