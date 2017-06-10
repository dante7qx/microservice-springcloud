package org.dante.springcloud.controller;

import org.dante.springcloud.dao.UserDao;
import org.dante.springcloud.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userDao.findOne(id);
		LOGGER.info("======================================> 开始执行");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("======================================> 执行结束");
		return user;
	}
	
}
