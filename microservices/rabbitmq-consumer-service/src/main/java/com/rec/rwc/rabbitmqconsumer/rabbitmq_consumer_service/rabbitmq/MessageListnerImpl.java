package com.rec.rwc.rabbitmqconsumer.rabbitmq_consumer_service.rabbitmq;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.rec.rwc.rabbitmqconsumer.rabbitmq_consumer_service.model.RecOrchestratorProcess;
import com.rec.rwc.rabbitmqconsumer.rabbitmq_consumer_service.service.MQConsumerService;

@Component
public class MessageListnerImpl implements MessageListener{

	@Autowired
	MQConsumerService inventoryService;
	
	@Autowired
    RestTemplate restTemplate;


	public void onMessage(String message) throws Exception {

		RecOrchestratorProcess[] recApps = restTemplate.postForObject("http://rec-orch-process-services/getProcessConfigDetails", message,RecOrchestratorProcess[].class);		
		List<RecOrchestratorProcess> orchProcess = Arrays.asList(recApps);


		Collections.sort(orchProcess);
		int len = orchProcess.size();
		int count = 0;
		
		for(RecOrchestratorProcess obj: orchProcess) {
			count = count + 1;
			if(null == obj.getStatus() || "N".equals(obj.getStatus())){
				JSONObject k = new JSONObject();
				k.put("inputConfig",obj.getInputConfig());
				k.put("processOrchId",obj.getId());

				restTemplate.postForObject("http://"+obj.getProcessUrl(), k.toJSONString(),String.class);
				
				break;
			}
		}
		
		if(count != 0 && len != count) {
			restTemplate.postForObject("http://rec-orch-process-services/publishQueue", message,List.class);

		}

	
		
	}
}


