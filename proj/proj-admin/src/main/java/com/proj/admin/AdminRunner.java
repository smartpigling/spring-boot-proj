package com.proj.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class AdminRunner {

	public static void main(String[] args) {
		SpringApplication.run(AdminRunner.class, args);
	}

}
