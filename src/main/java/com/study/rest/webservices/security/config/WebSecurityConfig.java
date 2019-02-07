package com.study.rest.webservices.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.rest.webservices.security.filter.JWTLogFilter;

/**
 * Default configuration for security with Spring Security.]
 * Override <code>configure</code> method to perform our logic to
 * request authorize.
 * 
 * @author Pedro Rodrigues (pedrovitorlima@gmail.com)
 * **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			//Everyone can access /jpa, even without authentication 
			.antMatchers("/jpa")
				.permitAll()
			//Endpoint /login is available only with post requests
			.antMatchers(HttpMethod.POST, "/login")
				.permitAll()
			//...others requests needs to be from authentication user
			.anyRequest()
				.authenticated()
				.and()
				//Execute filter arequest to manage authentication
				.addFilterBefore(new JWTLogFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//This is a default account
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("pass")
			.roles("ADMIN");
	}

}
