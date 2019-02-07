package com.study.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	/**
	 * This method creates a bean of {@link LocaleResolver} to put US as a default locale.
	 * When the request header was not setted the application will use this locale. 
	 * **/
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	/**
	 * This method creates a message source with a basename 'messages'.
	 * This can be used to internationalization. Others files needs to have the name starting with 'messages'
	 * and followed by _international-code. With this strategy the file messages-fr.properties will be used as a 
	 * source of french messages.
	 * 
	 * We dont need this because we already define the basename in application.properties
	 * **/
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

}

