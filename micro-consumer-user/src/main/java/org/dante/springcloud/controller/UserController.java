package org.dante.springcloud.controller;

import org.dante.springcloud.config.UserRibbonConfig;
import org.dante.springcloud.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 配置的服务实例必须大写，即 MICRO-PROVIDER-USER
 * 
 * @author dante
 *
 */
@RestController
@RibbonClient(name = "MICRO-PROVIDER-USER", configuration = UserRibbonConfig.class)
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
		
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		logger.info("Request to req /user/id {}.", id);
		User user = restTemplate.getForObject("http://micro-provider-user/user/"+id, User.class);
		return user;
	}
	
	@GetMapping("/test")
	public String testRibbonCustomerConfig() {
		ServiceInstance serviceInstance1 = loadBalancerClient.choose("micro-provider-user");
		logger.info("microservice->{}->{}:{}", serviceInstance1.getServiceId(), serviceInstance1.getHost(), serviceInstance1.getPort());
		ServiceInstance serviceInstance2 = loadBalancerClient.choose("micro-provider-user2");
		logger.info("microservice->{}->{}:{}", serviceInstance2.getServiceId(), serviceInstance2.getHost(), serviceInstance2.getPort());
		return null;
	}
}
