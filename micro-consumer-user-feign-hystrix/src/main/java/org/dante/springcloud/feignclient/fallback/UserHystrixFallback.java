package org.dante.springcloud.feignclient.fallback;

import java.util.List;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.UserFeignClient;
import org.dante.springcloud.vo.PageReq;
import org.dante.springcloud.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public class UserHystrixFallback implements UserFeignClient {

	@Override
	public User getUser(Long id) {
		return new User(id, "fallback-user", "熔断者-"+id, 31);
	}

	@Override
	public User getUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> addUser(User user) {
		return null;
	}

	@Override
	public List<User> delUser(Long id) {
		return null;
	}

	@Override
	public UserVO<User> getUserVo(PageReq pagereq) {
		return new UserVO<User>();
	}

	@Override
	public User getUserId(Long id) {
		return new User(id);
	}

}
