package org.dante.springcloud.config;

import org.dante.springcloud.annotation.BootStartExclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
@BootStartExclude
public class UserRibbonConfig {
	
	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}
