package com.softuni.eventem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventEmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventEmApplication.class, args);
	}

}
