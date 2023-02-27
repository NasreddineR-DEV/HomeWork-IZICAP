package com.izicap.discoveryservices.discoveryservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryservicesApplication.class, args);
	}

}
