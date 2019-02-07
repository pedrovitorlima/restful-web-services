package com.study.rest.webservices.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description="This is a basic representation of system user to test")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Past(message="Wtf? Is the Terminator here?")
	private Date birthDate;
	
	@Size(min=2, message="Name shoud have a min size of 2.")
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<Post> posts;
	
	public User() {
		
	}
	
	public User(int id, Date birthDate, String name) {
		super();
		this.id = id;
		this.birthDate = birthDate;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	
	
}
