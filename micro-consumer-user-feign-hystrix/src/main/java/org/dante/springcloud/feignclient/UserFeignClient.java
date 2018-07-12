package org.dante.springcloud.feignclient;

import java.util.List;

import org.dante.config.feign.UserFeignClientConfig;
import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.fallback.UserHystrixFallback;
import org.dante.springcloud.vo.PageReq;
import org.dante.springcloud.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "micro-provider-user", fallback = UserHystrixFallback.class, configuration = UserFeignClientConfig.class)
public interface UserFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public User getUser(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.POST, value = "/user/{id}")
	public User getUser(@PathVariable("id") Long id, User user);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user_id{id}")
	public User getUserId(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.POST, value = "/user-list")
	public List<User> getUserList(User user);

	@RequestMapping(method = RequestMethod.PUT, value = "/add-user")
	public List<User> addUser(User user);

	@RequestMapping(method = RequestMethod.DELETE, value = "/del-user/{id}")
	public List<User> delUser(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user-vos")
	public UserVO<User> getUserVo(PageReq pagereq);
}
