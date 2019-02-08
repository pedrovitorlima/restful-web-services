package com.study.rest.webservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.rest.webservices.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByLogin(String login);
}
