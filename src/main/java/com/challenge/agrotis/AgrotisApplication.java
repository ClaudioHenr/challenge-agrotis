package com.challenge.agrotis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.challenge.agrotis"})
public class AgrotisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrotisApplication.class, args);
	}

}
