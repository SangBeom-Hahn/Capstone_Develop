package com.kyonggi.Capstone_Develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CapstoneDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneDevelopApplication.class, args);
	}
}
