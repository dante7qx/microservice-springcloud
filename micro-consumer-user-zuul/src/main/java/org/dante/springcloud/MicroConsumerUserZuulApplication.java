package org.dante.springcloud;

import org.dante.springcloud.config.zuul.CustomerZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class MicroConsumerUserZuulApplication {
	
	@Bean
	public CustomerZuulFilter customerZuulFilter() {
		return new CustomerZuulFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroConsumerUserZuulApplication.class, args);
	}
}
