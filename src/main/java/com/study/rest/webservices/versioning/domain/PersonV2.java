package com.study.rest.webservices.versioning.domain;

public class PersonV2 {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonV2(String name) {
		super();
		this.name = name;
	}
	
	public PersonV2() {
		
	}
	
}
