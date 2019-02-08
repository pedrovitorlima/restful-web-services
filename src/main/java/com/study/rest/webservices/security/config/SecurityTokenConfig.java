package com.study.rest.webservices.security.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.rest.webservices.security.filter.JWTLoginFilter;
import com.study.rest.webservices.security.filter.JWTTokenuthenticationFilter;

@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling()
				.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
				//This filter will process new logins
				.addFilter(new JWTLoginFilter(authenticationManager(), jwtConfig))
				.authorizeRequests()
				//This will configure free access to the authentication URL
				.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
				.and()
				//This filter will process requests and validate tokens in Authorization access
					.addFilterAfter(new JWTTokenuthenticationFilter(jwtConfig), 
							UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
}
