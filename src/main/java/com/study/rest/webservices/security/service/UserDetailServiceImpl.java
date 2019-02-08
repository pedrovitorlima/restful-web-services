package com.study.rest.webservices.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.rest.webservices.domain.User;
import com.study.rest.webservices.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByLogin(username);
//		User user = userRepository.findById(1).get();
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		org.springframework.security.core.userdetails.User userSpr = 
				new org.springframework.security.core.userdetails.User(
					user.getLogin(), 
					//doing this because we are working with raw passwords in user table
					//take a look on data.sql!
					encoder.encode(user.getPassword()), 
					grantedAuthorities); 
		
		return userSpr;
		
	}

}
