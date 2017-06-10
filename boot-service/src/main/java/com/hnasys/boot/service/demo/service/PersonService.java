package com.hnasys.boot.service.demo.service;

import org.springframework.data.domain.Page;

import com.hnasys.boot.dao.demo.domain.Person;

public interface PersonService {
	public Page<Person> findPage() throws Exception;
}
