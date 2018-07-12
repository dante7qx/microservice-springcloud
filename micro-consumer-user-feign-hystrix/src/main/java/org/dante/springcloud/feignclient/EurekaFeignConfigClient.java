package org.dante.springcloud.feignclient;


import org.dante.config.feign.EurekaFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "micro-eureka-standalone", url="http://localhost:8761/eureka", configuration = EurekaFeignClientConfig.class)
public interface EurekaFeignConfigClient {
	@RequestLine("GET /apps/{serviceid}")
    public String getEurekaInstance(@Param("serviceid") String serviceid);
}
