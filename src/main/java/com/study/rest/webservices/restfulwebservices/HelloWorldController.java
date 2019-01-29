package com.study.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.study.rest.webservices.restfulwebservices.domain.User;
import com.study.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.study.rest.webservices.restfulwebservices.service.UserDaoService;

@RestController
public class HelloWorldController {

	@Autowired
 	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> returnUsers() {
		return 	service.findAll();
	}
	
	@GetMapping("/users/say-hi")
	public String sayHi() {
		return "hi my friend";
	}
	
	@GetMapping("/users/say-hi/{name}")
	public String sayHiCustomized(@PathVariable("name") String name) {
		return String.format("Hi, %s", name);
	}
	
	@GetMapping("/users/{id}")
	public User retrieveOne(@PathVariable("id") int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		service.save(user); 
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
