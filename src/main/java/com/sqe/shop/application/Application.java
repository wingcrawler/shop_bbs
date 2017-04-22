package com.sqe.shop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-context.xml")
@ComponentScan(basePackages = "com.sqe")  
@EnableAutoConfiguration
/*@SpringBootApplication*/
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

}
