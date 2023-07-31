package com.fin.love;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FindLoveByHobbysApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindLoveByHobbysApplication.class, args);
		
	}
}
