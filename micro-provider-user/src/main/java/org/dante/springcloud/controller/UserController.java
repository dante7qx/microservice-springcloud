package org.dante.springcloud.controller;

import java.util.List;

import org.dante.springcloud.dao.UserDao;
import org.dante.springcloud.domain.User;
import org.dante.springcloud.vo.PageReq;
import org.dante.springcloud.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
@RefreshScope	// 修改配置文件后，能够自动刷新加载，POST 请求 Config Server 的 /refresh
public class UserController {
	
//	@Value("${config.foo}")
	private String propVal;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/user/prop")
	public String getProp() {
		return "config.foo --> [" + propVal + "]";
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userDao.findOne(id);
		
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
	
	@PostMapping("/user_id")
	public User getUserId(@RequestParam("id") Long id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
	@PostMapping("/user-list")
	public List<User> getUserList(@RequestBody User user) {
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(user.getId(), user.getAccount()+"_"+i));
		}
		return list;
	}
	
	@PostMapping("/user-vos")
	public UserVO<User> getUserVoList(@RequestBody PageReq pageReq) {
		UserVO<User> vo = new UserVO<User>();
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(Long.valueOf(i), "user_"+i));
		}
		vo.setDatas(list);
		return vo;
	}
	
	@PutMapping("/add-user")
	public List<User> addUser(@RequestBody User user) {
		userDao.save(user);
		return userDao.findAll(new Sort(Direction.DESC, "id"));
	}
	
	@DeleteMapping("/del-user/{id}")
	public List<User> delUser(@PathVariable Long id) {
		userDao.delete(id);
		return userDao.findAll(new Sort(Direction.DESC, "id"));
	}
	
}
