package com.rec.rwc.rabbitmqconsumer.rabbitmq_consumer_service.rabbitmq;

public interface MessageListener {
	
	public void onMessage(String message) throws Exception;
}
