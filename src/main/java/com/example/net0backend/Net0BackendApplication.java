package com.example.net0backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Net0BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Net0BackendApplication.class, args);
	}

}
