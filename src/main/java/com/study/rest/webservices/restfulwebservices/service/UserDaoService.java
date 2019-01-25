package com.study.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.study.rest.webservices.restfulwebservices.domain.User;

@Component
public class UserDaoService {
	
	public static List<User> users;
	private static int countUsers = 0;
	
	static {
		users = new ArrayList<User>();
		users.add(new User(countUsers++, "raphael", "rapha", new Date()));
		users.add(new User(countUsers++, "pedro", "1234", new Date()));
		users.add(new User(countUsers++, "michael", "mitt", new Date()));
	}

	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
