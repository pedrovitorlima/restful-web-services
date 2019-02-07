package com.study.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.rest.webservices.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
