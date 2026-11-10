package com.murilodcosta.skriwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SkriwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkriwoApplication.class, args);
	}

}
