package com.kyonggi.Capstone_Develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CapstoneDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneDevelopApplication.class, args);
	}

}
