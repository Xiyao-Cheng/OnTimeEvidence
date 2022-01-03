package com.rec.orch.service;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.orch.queue.MessageProducer;

@Service
public class RabbitMessageService {

	@Autowired
	private MessageProducer messageProducer;
	
	public void sendRecommendarMessage(Long recId, Long clientId) throws Exception
	{
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("recId", recId);
		jsonObject.put("clientId", clientId);
		String msg = jsonObject.toJSONString();
	
		messageProducer.sendMessage(msg);
		
	}


}
