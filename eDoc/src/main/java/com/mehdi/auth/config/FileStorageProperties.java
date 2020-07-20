package com.mehdi.auth.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	@Getter
	@Setter
	private String uploadDir;
}
