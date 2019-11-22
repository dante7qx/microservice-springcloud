package org.dante.springcloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.dante.springcloud.dao.UserDao;
import org.dante.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope	// 修改配置文件后，能够自动刷新加载，POST 请求 Config Server 的 /refresh
public class UserController {
	
	@Value("${config.foo}")
	private String foo;
	@Value("${config.bar}")
	private String bar;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/user/prop")
	public String getProp() {
		return "config.foo --> [" + foo + "], bar --> [" + bar + "]";
	} 
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userDao.getOne(id);
		return user;
	}
	
	@GetMapping("/user/all")
	public List<User> getAllUser() {
		return userDao.findAll(new Sort(Direction.DESC, "id"));
	}
	
	@PostMapping("/user/{id}")
	public User getUser(@PathVariable Long id, @RequestBody User usr) {
		User user = new User();
		user.setId(id);
		user.setAccount(usr.getAccount());
		user.setName(usr.getName());
		return user;
	}
	
	@PostMapping("/user-list")
	public List<User> getUserList(@RequestBody User user) {
		List<User> list = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(user.getId(), user.getAccount()+"_"+i));
		}
		return list;
	}
	
	@PutMapping("/add-user")
	public List<User> addUser(@RequestBody User user) {
		userDao.save(user);
		return userDao.findAll(new Sort(Direction.DESC, "id"));
	}
	
	@DeleteMapping("/del-user/{id}")
	public List<User> delUser(@PathVariable Long id) {
		userDao.deleteById(id);
		return userDao.findAll(new Sort(Direction.DESC, "id"));
	}
	
}
