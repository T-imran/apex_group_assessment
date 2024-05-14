package com.example.spiringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpiringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiringSecurityApplication.class, args);
	}

}
