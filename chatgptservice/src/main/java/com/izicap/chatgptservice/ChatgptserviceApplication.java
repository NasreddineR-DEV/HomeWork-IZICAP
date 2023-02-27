package com.izicap.chatgptservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChatgptserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatgptserviceApplication.class, args);
	}

}
