package org.dante.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/hello")
	public String hello() {
		try {
			List<String> services = discoveryClient.getServices();
			if(!CollectionUtils.isEmpty(services)) {
				for (String service : services) {
					log.info("Service -> {}", service);
				}
			}
		} catch (Exception e) {
			log.error("发生异常！", e);
		}
		return "Hello Spring Cloud Kubernetes!";
	}
	
}
