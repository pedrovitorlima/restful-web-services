package com.study.rest.webservices.restfulwebservices.domain;

import java.util.Date;

public class User {

	private int id;
	private String login;
	private String password;
	private Date birthDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public User(int id, String login, String password, Date birthDate) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
	}
	

	
}
