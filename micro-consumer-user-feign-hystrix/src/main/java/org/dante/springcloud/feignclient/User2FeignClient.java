package org.dante.springcloud.feignclient;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.fallback.User2HystrixFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "micro-provider-user2", fallbackFactory = User2HystrixFallbackFactory.class)
public interface User2FeignClient {
	
	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id);
	
	
}
