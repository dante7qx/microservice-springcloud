package org.dante.springcloud.controller;

import org.dante.springcloud.dao.UserDao;
import org.dante.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userDao.findOne(id);
		return user;
	}
	
}
