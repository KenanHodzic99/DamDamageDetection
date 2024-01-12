package com.kenanhodzic.damdamagedetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class DamDamageDetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamDamageDetectionApplication.class, args);
	}

}
