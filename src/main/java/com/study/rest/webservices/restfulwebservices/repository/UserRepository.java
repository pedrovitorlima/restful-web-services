package com.study.rest.webservices.restfulwebservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.study.rest.webservices.restfulwebservices.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
