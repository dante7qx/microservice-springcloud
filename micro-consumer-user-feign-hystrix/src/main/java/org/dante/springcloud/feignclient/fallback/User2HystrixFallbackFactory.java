package org.dante.springcloud.feignclient.fallback;

import org.dante.springcloud.domain.User;
import org.dante.springcloud.feignclient.User2FeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 通过配置fallbackFactory，可以获取失败的原因
 * 目前不支持返回com.netflix.hystrix.HystrixCommand和rx.Observable的方法的回退
 * 
 * @author dante
 *
 */
@Component
public class User2HystrixFallbackFactory implements FallbackFactory<User2FeignClient>{
	private final Logger logger = LoggerFactory.getLogger(User2HystrixFallbackFactory.class);
	
	@Override
	public User2FeignClient create(Throwable cause) {
		return new User2FeignClient(){
			@Override
			public User getUser(Long id) {
				logger.error("getUser id {} error.", id, cause);
				User user = new User();
				user.setId(id);
				user.setName("失败原因：" + cause.getMessage());
				return user;
			}
		};
	}

}
