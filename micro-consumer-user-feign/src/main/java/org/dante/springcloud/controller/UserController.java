package org.dante.springcloud.controller;

import java.util.List;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.UserFeignClient;
import org.dante.springcloud.feignclient.UserFeignConfigClient;
import org.dante.springcloud.vo.ParamsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
		
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private UserFeignConfigClient userFeignConfigClient;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		return userFeignClient.getUser(id);
	}
	
	@GetMapping("/eureka-instance/{instanceId}")
	public String getEurekaInstance(@PathVariable String instanceId) {
		logger.info("Load eureka client instance is {}", instanceId);
		return userFeignConfigClient.getEurekaInstance(instanceId);
	}
	
	@GetMapping("/user/{id}/{account}")
	public User getUser(@PathVariable Long id,@PathVariable String account) {
		User user = new User();
		user.setAccount(account);
		return userFeignClient.getUser(id, user);
	}
	
	@PostMapping("/user-list")
	public List<User> getUser(@RequestBody User usr) {
		User user = new User();
		user.setId(usr.getId());
		user.setAccount(usr.getAccount());
		user.setName(usr.getName());
		return userFeignClient.getUserList(user);
	}
	
	/**
	  	{
		"id":31,
		"account":"snake",
		"name":"固态蛇",
		"age":32,
		"balance": 189.75
		}
	 */
	@PutMapping("/add-user")
	public List<User> addUser(@RequestBody User usr) {
		return userFeignClient.addUser(usr);
	}
	
	@DeleteMapping("/del-user/{id}")
	public List<User> addUser(@PathVariable Long id) {
		return userFeignClient.delUser(id);
	}
	
	@GetMapping("/query")
	public String springQueryMap() {
		ParamsVO vo = new ParamsVO();
		vo.setParam1("param1");
		vo.setParam2("参数2");
		return userFeignClient.stringQueryMap(vo);
	}
}
