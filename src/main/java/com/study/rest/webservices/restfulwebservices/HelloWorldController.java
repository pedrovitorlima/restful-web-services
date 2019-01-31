package com.study.rest.webservices.restfulwebservices;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.study.rest.webservices.restfulwebservices.domain.User;
import com.study.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.study.rest.webservices.restfulwebservices.service.UserDaoService;

@RestController
public class HelloWorldController {

	/**
	 * This object can be used to return messages with correct locale of request.
	 * The {@link Locale} needs to be passed as a arg in method.
	 * **/
	@Autowired
	private MessageSource messageSource;
	
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
	
//	@GetMapping("/users/say-hi-internationalized")
//	public String helloWorldInternationalized(
//			@RequestHeader(name="Accept-Language", required=false) Locale locale) {
//		//above, declaring a parameter with request header value
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}
	
	@GetMapping("/users/say-hi-internationalized")
	public String helloWorldInternationalized() {
		//here we dont need to declare the header 
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/users/say-hi/{name}")
	public String sayHiCustomized(@PathVariable("name") String name) {
		return String.format("Hi, %s", name);
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveOne(@PathVariable("id") int id) {
		User user = service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		//HATEOAS - how to get all users
		//STEP 1) creating resource 
		Resource<User> resource = new Resource<User>(user);
		
		//STEP 2) creating links and bound then to methods of this class
		ControllerLinkBuilder linkAllUsers = linkTo(methodOn(this.getClass()).returnUsers());
		ControllerLinkBuilder linkDelUser = linkTo(methodOn(this.getClass()).deleteOne(id));
		
		//STEP 3) set descriptions on links
		resource.add(linkAllUsers.withRel("all-users"));
		resource.add(linkDelUser.withRel("remove-users"));
		
		//Thats it! just return
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		service.save(user); 
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteOne(@PathVariable("id") int id) {
		User user = service.deleteById(id);
		
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		return user;
	}
}
