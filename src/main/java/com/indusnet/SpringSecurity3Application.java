package com.indusnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringSecurity3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity3Application.class, args);
	}

}
