package org.dante.springcloud.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HealthController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${app.name}")
	private String name;
	
	@GetMapping("/health")
	public String health() {
		log.info("Request to check health at {} ...", Instant.now().toString());
		return "ok->".concat(name);
	}
	
	@GetMapping("/app/{app}")
	public String serviceUrl(@PathVariable String app) {
		List<ServiceInstance> list = discoveryClient.getInstances(app);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0).getInstanceId();
		}
		return null;
	}
	
}
