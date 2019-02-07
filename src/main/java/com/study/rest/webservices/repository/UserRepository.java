package com.study.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.rest.webservices.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
