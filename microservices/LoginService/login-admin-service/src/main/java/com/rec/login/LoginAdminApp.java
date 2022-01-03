package com.rec.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginAdminApp {

	public static void main(String[] args) {
		SpringApplication.run(LoginAdminApp.class, args);
	}

}
