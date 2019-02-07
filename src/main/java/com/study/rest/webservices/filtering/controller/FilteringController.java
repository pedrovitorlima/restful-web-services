package com.study.rest.webservices.filtering.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.study.rest.webservices.domain.SomeBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		//Create a filter to say 'I only want field1 and field2'
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		//This is something like a list of filters, now lets add previous filter on our collection
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		//Now lets put our bean inside a class to process the filter
		//Look on SomeBean, in the top of class declaration we define "SomeBeanFilter" as
		//a valid filter to SomeBean
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeans() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value4", "value5", "value6"),
				new SomeBean("value7", "value8", "value9"));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
