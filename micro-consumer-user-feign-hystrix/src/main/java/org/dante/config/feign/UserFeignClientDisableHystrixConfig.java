package org.dante.config.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;

/**
 * 禁用Hystrix，因为Feign默认的 Builder 是 HystrixFeign.Builder
 * 
 * @author dante
 *
 */
@Configuration
public class UserFeignClientDisableHystrixConfig {
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
