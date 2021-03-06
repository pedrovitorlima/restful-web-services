package com.study.rest.webservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.study.rest.webservices.domain.User;

@Component
public class UserDaoService {
	
	public static List<User> users;
	private static int countUsers = 0;
	
	static {
		users = new ArrayList<User>();
		users.add(new User(countUsers++, new Date(), "rapha"));
		users.add(new User(countUsers++, new Date(), "rapha"));
		users.add(new User(countUsers++, new Date(), "rapha"));
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

	public void save(User user) {
		if (user != null) {
			user.setId(countUsers++);
		}
		users.add(user);
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
