package com.douzone.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
public class BootInitializer extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MysiteApplication.class, args);
	}
}