package com.poriadin.brdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrdoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrdoApplication.class, args);
	}

}
