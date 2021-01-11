package org.dante.springcloud.controller;

import java.util.List;

import org.dante.springcloud.dao.UserDao;
import org.dante.springcloud.domain.User;
import org.dante.springcloud.vo.PageReq;
import org.dante.springcloud.vo.UserRespDTO;
import org.dante.springcloud.vo.UserVO;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RefreshScope	// 修改配置文件后，能够自动刷新加载，POST 请求 Config Server 的 /refresh
public class UserReactController {
	
	@Value("${config.foo}")
	private String propVal;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/userx/prop")
	public Mono<String> getProp() {
		return Mono.just("config.foo --> [" + propVal + "]");
	}
	
	@GetMapping("/userx/{id}")
	public Mono<UserRespDTO> getUser(@PathVariable Long id) {
		UserRespDTO userResp = new UserRespDTO();
		User user = userDao.getOne(id);
		BeanUtils.copyProperties(user, userResp);
		return Mono.just(userResp);
	}
	
	@GetMapping("/userx/all")
	public Flux<User> getAllUser() {
		return Flux.just(userDao.findAll(Sort.by(Direction.DESC, "id")).toArray(new User[0]));
	}
	
	@PostMapping("/userx/{id}")
	public Mono<UserRespDTO> getUser(@PathVariable Long id, @RequestBody User usr) {
		UserRespDTO user = new UserRespDTO();
		user.setId(id);
		user.setAccount(usr.getAccount());
		user.setName(usr.getName());
		return Mono.just(user);
	}
	
	@PostMapping("/userx_id")
	public Mono<UserRespDTO> getUserId(@RequestParam("id") Long id) {
		UserRespDTO user = new UserRespDTO();
		user.setId(id);
		return Mono.just(user);
	}
	
	@PostMapping("/userx-list")
	public Mono<List<User>> getUserList(@RequestBody User user) {
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(user.getId(), user.getAccount()+"_"+i));
		}
		return Mono.just(list);
	}
	
	@PostMapping("/userx-vos")
	public Mono<UserVO<User>> getUserVoList(@RequestBody PageReq pageReq) {
		UserVO<User> vo = new UserVO<User>();
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			list.add(new User(Long.valueOf(i), "user_"+i));
		}
		vo.setDatas(list);
		return Mono.just(vo);
	}
	
	@PutMapping("/addx-user")
	public Flux<User> addUser(@RequestBody User user) {
		userDao.save(user);
		return Flux.just(userDao.findAll(Sort.by(Direction.DESC, "id")).toArray(new User[0]));
	}
	
	@DeleteMapping("/delx-user/{id}")
	public Flux<User> delUser(@PathVariable Long id) {
		userDao.deleteById(id);
		return Flux.just(userDao.findAll(Sort.by(Direction.DESC, "id")).toArray(new User[0]));
	}
	
}
