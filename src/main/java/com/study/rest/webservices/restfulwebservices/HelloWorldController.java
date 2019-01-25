package com.study.rest.webservices.restfulwebservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.study.rest.webservices.restfulwebservices.domain.User;
import com.study.rest.webservices.restfulwebservices.service.UserDaoService;

@RestController
public class HelloWorldController {

	@Autowired
 	private UserDaoService service;
	
	@GetMapping("/users/list")
	public List<User> returnUsers() {
		return 	service.findAll();
	}
	
	@GetMapping("/say-hi")
	public String sayHi() {
		return "hi my friend";
	}
	
	@GetMapping("say-hi/{name}")
	public String sayHiCustomized(@PathVariable("name") String name) {
		return String.format("Hi, %s", name);
	}
	
	@GetMapping("/users/list/{id}")
	public User retrieveOne(@PathVariable("id") int id) {
		return service.findOne(id);
	}
}
