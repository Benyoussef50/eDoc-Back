package com.mehdi.auth;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer {

	 @Override
	    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
	        return application.sources(EDocApplication.class);
	    }
}
