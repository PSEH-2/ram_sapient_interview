package com.ram.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RamWeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamWeatherServiceApplication.class, args);
	}

}
