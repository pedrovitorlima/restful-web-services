package com.study.rest.webservices.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.rest.webservices.security.config.JwtConfig;
import com.study.rest.webservices.security.domain.Credentials;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Pedro Rodrigues (pedrovitorlima@gmail.com)
 * **/
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authManager;
	private final JwtConfig jwtConfig;
	
	public JWTLoginFilter(
			AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		
		this.setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response){
		
		try {
			Credentials creds = new ObjectMapper().readValue(
					request.getInputStream(), Credentials.class);
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList());
			return authManager.authenticate(authToken);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
				.setSubject(auth.getName())
				.claim("authorities", auth.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
				.compact();
		
		//Bearer <token>
		response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix().concat(token));
		
	}
	

}
