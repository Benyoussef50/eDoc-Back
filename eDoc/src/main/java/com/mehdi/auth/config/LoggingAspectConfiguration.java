package com.mehdi.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import com.mehdi.auth.config.logging.LoggingAspect;


public class LoggingAspectConfiguration {
	 @Bean
	    @Profile(ConfigConstants.PROFILE_DEVELOPMENT)
	    public LoggingAspect loggingAspect(final Environment env) {
	        return new LoggingAspect(env);
	    }
}
