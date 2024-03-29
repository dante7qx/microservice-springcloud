package org.dante.springcloud.controller;

import org.dante.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置的服务实例必须大写，即 MICRO-PROVIDER-USER
 * 
 * @author dante
 *
 */
@Slf4j
@RestController
@org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient(name = "${loadbalancer.client.name}")
public class UserController {
	
	@Autowired
	private Environment environment;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		log.info("Request to req /user/id {}.", id);
		User user = restTemplate.getForObject("http://MICRO-PROVIDER-USER/user/"+id, User.class);
		return user;
	}
	
	@GetMapping("/test")
	public String testRibbonCustomerConfig() {
		log.info("Env Property (loadbalancer.client.name) -> {}", environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME));
		ServiceInstance serviceInstance1 = loadBalancerClient.choose("micro-provider-user");
		if(serviceInstance1 != null) {
			log.info("microservice->{}->{}:{}", serviceInstance1.getServiceId(), serviceInstance1.getHost(), serviceInstance1.getPort());
		} else {
			log.info("服务 micro-provider-user 未注册。。。");
		}
		ServiceInstance serviceInstance2 = loadBalancerClient.choose("micro-provider-user2");
		if(serviceInstance2 != null) {
			log.info("microservice->{}->{}:{}", serviceInstance2.getServiceId(), serviceInstance2.getHost(), serviceInstance2.getPort());
		} else {
			log.info("服务 micro-provider-user2 未注册。。。");
		}
		return null;
	}
}
