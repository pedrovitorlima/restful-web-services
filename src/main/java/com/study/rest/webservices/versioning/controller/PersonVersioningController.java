package com.study.rest.webservices.versioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.rest.webservices.versioning.domain.PersonV1;
import com.study.rest.webservices.versioning.domain.PersonV2;

@RestController
public class PersonVersioningController {

	//URI versioning: Twitter style
	@GetMapping(value="/v1/person" )
	public PersonV1 personVersioningV1() {
		return new PersonV1("Antonio Banderas");
	}
	
	@GetMapping(value="/v2/person")
	public PersonV1 personVersioningV2() {
		return new PersonV1("Antonio Banderas");
	}
	
	//Request parameter versioning: Amazon style
	@GetMapping(value="/person", params="version=1")
	public PersonV1 personV1() {
		return new PersonV1("Antonio Banderas");
	}
	
	@GetMapping(value="/person", params="version=2")
	public PersonV1 personV2() {
		return new PersonV1("Antonio Banderas");
	}
	
	//Custom headers versioning: Microsoft style
	@GetMapping(value="/person", headers="X-API-VERSION=1")
	public PersonV1 personHeaderV1() {
		return new PersonV1("Antonio Banderas");
	}
	
	@GetMapping(value="/person", headers="X-API-VERSION=2")
	public PersonV2 personHeaderV2() {
		return new PersonV2("Bruce Dickinson");
	}
	
	//Media type versioning: GitHub style
	@GetMapping(value="/person", consumes="application/vnd.company.app-v1+json")
	public PersonV1 personConsumesV1() {
		return new PersonV1("Antonio Banderas");
	}
	
	@GetMapping(value="/person", consumes="application/vnd.company.app-v2+json")
	public PersonV2 personConsumesV2() {
		return new PersonV2("Bruce Dickinson");
	}
	
}
