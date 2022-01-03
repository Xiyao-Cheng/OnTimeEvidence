package com.rec.orch.queue;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

	public static final String ROUTING_KEY = "recommender-queue";
	
	Queue queue() {
		return new Queue(ROUTING_KEY, true);
	}
	
	TopicExchange exchange() {
		return new TopicExchange("recommender-exchange");
	}
	
	
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
}
