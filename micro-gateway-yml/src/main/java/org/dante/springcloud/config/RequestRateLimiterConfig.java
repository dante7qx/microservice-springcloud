package org.dante.springcloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class RequestRateLimiterConfig {
	
	@Bean
	KeyResolver remoteAddrKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
	
	@Bean
	KeyResolver currentUserKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}
}
