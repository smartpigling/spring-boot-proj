package com.proj.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogRunner {
	
	private static final Logger logger =LoggerFactory.getLogger(LogRunner.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LogRunner.class, args);
		
		logger.debug("logging debug test!!!!!!!!!!!!!!!!!!");
		logger.error("logging error test!!!!!!!!!!!!!!!!!!");
		logger.info("aaaaaaaaaaaaaaaa");
		logger.info("aaaaaaaaaaaaaaaa XX ");
		logger.info("cccccccccccccccc XXXXXXX ");
		logger.info("cccccccccccccccc xx ");
	}

}
