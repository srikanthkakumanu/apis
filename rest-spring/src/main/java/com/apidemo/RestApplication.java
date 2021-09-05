package com.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class provides the bootstrap for the Spring Boot application that is 
 * executed in the main method. You need to pass the class that is executed.
 */ 
@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
