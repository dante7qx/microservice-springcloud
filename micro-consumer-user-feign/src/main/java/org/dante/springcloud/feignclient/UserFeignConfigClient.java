package org.dante.springcloud.feignclient;


import org.dante.config.UserFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "micro-eureka-standalone", url="http://localhost:8761/eureka", configuration = UserFeignClientConfig.class)
public interface UserFeignConfigClient {
	
	/**
	 * Feign 原生注解
	 * 
	 * @param serviceid
	 * @return
	 */
	@RequestLine("GET /apps/{serviceid}")
    public String getEurekaInstance(@Param("serviceid") String serviceid);
}
