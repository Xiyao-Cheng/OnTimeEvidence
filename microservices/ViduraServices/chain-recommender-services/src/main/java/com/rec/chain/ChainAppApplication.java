package com.rec.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChainAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChainAppApplication.class, args);
	}

}
