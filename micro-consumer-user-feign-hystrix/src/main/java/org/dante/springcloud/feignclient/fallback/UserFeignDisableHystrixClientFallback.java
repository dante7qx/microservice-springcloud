package org.dante.springcloud.feignclient.fallback;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.UserFeignNoHystrixClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignDisableHystrixClientFallback implements UserFeignNoHystrixClient {

	@Override
	public User getUser(Long id) {
		return new User(id, "disable-hystrix-fallback-user", "熔断者-"+id, 31);
	}


}
