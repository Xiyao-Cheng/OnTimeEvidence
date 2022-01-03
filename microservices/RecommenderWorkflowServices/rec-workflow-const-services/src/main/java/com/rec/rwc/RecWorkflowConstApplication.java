package com.rec.rwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RecWorkflowConstApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecWorkflowConstApplication.class, args);
	}

}
