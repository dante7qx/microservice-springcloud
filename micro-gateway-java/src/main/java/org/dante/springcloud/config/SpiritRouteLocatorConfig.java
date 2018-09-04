package org.dante.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiritRouteLocatorConfig {

	private static final String TARGET_URI = "http://localhost:8101";

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route", r -> r.order(-1).path("/x/**").filters(f -> f.stripPrefix(1)).uri(TARGET_URI))
				.route(r -> r.order(0).query("k", "k").filters(f -> f.addResponseHeader("v", "v-k")).uri("https://baidu.com"))
			.build();
	}

}
