package org.dante.springcloud.controller;

import java.util.List;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.EurekaFeignConfigClient;
import org.dante.springcloud.feignclient.User2FeignClient;
import org.dante.springcloud.feignclient.UserFeignClient;
import org.dante.springcloud.feignclient.UserFeignNoHystrixClient;
import org.dante.springcloud.vo.PageReq;
import org.dante.springcloud.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class UserController {

	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private User2FeignClient user2FeignClient;
	@Autowired
	private UserFeignNoHystrixClient userFeignNoHystrixClient;
	@Autowired
	private EurekaFeignConfigClient userFeignConfigClient;

	/**
	 * execution.isolation.strategy：THREAD（HystrixCommand在一个单独的线程执行）-- 推荐（默认配置）
	 * execution.isolation.strategy：SEMAPHORE（HystrixCommand和主体在同一个线程执行）
	 * execution.isolation.thread.timeoutInMilliseconds（Hystrix默认的超时时间1s，对于耗时的调用可配置）
	 * 
	 * @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") })
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	@HystrixCommand
	public User getUser(@PathVariable Long id) {
		return userFeignClient.getUser(id);
	}

	@GetMapping("/eureka-instance/{instanceId}")
	public String getEurekaInstance(@PathVariable String instanceId) {
		return userFeignConfigClient.getEurekaInstance(instanceId);
	}

	@GetMapping("/user/{id}/{account}")
	public User getUser(@PathVariable Long id, @PathVariable String account) {
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
	 * { "id":31, "account":"snake", "name":"固态蛇", "age":32, "balance": 189.75 }
	 */
	@PutMapping("/add-user")
	public List<User> addUser(@RequestBody User usr) {
		return userFeignClient.addUser(usr);
	}

	@DeleteMapping("/del-user/{id}")
	public List<User> addUser(@PathVariable Long id) {
		return userFeignClient.delUser(id);
	}
	
	@GetMapping("/user2/{id}")
	@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000") })
	public User getUser2(@PathVariable Long id) {
		return user2FeignClient.getUser(id);
	}
	
	@PostMapping("/user-vos")
	public UserVO<User> getUserVo(PageReq pagereq) {
		return userFeignClient.getUserVo(pagereq);
	}
	
	@PostMapping("/user_id/{id}")
	public User user_id(@PathVariable Long id) {
		return userFeignClient.getUserId(id);
	}
	
	@GetMapping("/user3/{id}")
	@HystrixCommand
	public User getUser3(@PathVariable Long id) {
		return userFeignNoHystrixClient.getUser(id);
	}
}
