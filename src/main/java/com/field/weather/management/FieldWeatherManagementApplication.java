package com.field.weather.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.field.weather.management.*")
@EnableAutoConfiguration
public class FieldWeatherManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FieldWeatherManagementApplication.class, args);
	}

}