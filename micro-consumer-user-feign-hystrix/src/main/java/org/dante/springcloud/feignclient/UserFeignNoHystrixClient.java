package org.dante.springcloud.feignclient;

import org.dante.config.feign.UserFeignClientDisableHystrixConfig;
import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.fallback.UserFeignDisableHystrixClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "micro-provider-user3", configuration = UserFeignClientDisableHystrixConfig.class, fallback = UserFeignDisableHystrixClientFallback.class)
public interface UserFeignNoHystrixClient {
	
	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id);

}
