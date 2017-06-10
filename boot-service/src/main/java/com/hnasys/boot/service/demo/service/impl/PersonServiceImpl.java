package com.hnasys.boot.service.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hnasys.boot.dao.demo.dao.PersonDao;
import com.hnasys.boot.dao.demo.domain.Person;
import com.hnasys.boot.service.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public Page<Person> findPage() throws Exception {
		return personDao.findAll(new PageRequest(0, 4, new Sort(Direction.DESC, "age")));
	}

	
}
