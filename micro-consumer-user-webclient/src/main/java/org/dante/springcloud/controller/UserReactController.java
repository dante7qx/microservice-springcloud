package org.dante.springcloud.controller;

import org.dante.springcloud.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RestController
public class UserReactController {
	
	private final Logger logger = LoggerFactory.getLogger(UserReactController.class);
		
	@Autowired
	@LoadBalanced
    private WebClient.Builder webClientBuilder;
	
	@Autowired
    private LoadBalancerExchangeFilterFunction lbFunction;
	
	@GetMapping("/user/{id}")
	public Mono<User> getUser(@PathVariable Long id) {
		logger.info("Request to req /user/id {}.", id);
		return webClientBuilder.build().get().uri("/user/".concat(id+"")).retrieve().bodyToMono(User.class);
	}
	
	@GetMapping("/userx/{id}")
	public Mono<User> getUserx(@PathVariable Long id) {
		logger.info("Request to req /userx/id {}.", id);
		return webClientBuilder.build().get().uri("/userx/".concat(id+"")).retrieve().bodyToMono(User.class);
	}
	
	@GetMapping("/userc/{id}")
	public Mono<User> getUserc(@PathVariable Long id) {
		logger.info("Request to req /userc/id {}.", id);
		return WebClient.builder()
				.baseUrl("http://micro-provider-user")
				.filter(lbFunction)
				.build()
				.get()
				.uri("/userx/".concat(id+""))
				.retrieve()
				.bodyToMono(User.class);
	}
}
