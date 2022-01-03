package com.rec.orch.queue;

import java.io.IOException;
import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String message)  throws IOException
	{
		System.out.println("sendMessage in MessageProducer start : " + new Date());
		
		rabbitTemplate.convertAndSend(RabbitmqConfig.ROUTING_KEY, message);
		
		System.out.println("sendMessage in MessageProducer end : " + new Date());
		System.out.println("Message sent");
	}
	
}
