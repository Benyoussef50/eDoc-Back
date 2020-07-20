package com.mehdi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mehdi.auth.config.FileStorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class EDocApplication {

	private static final Logger log = LoggerFactory.getLogger(EDocApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EDocApplication.class, args);
	}
	
	
	
	
}
