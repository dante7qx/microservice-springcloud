package org.dante.springcloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LoadBalancerConfig {
	
	/**
	 * 切换 LoadBalancer 的实现类，默认是RoundRobinLoadBalancer， 切换为RandomLoadBalancer
	 * 
	 * @param environment
	 * @param loadBalancerClientFactory
	 * @return
	 */
	@Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
            LoadBalancerClientFactory loadBalancerClientFactory) {
		// 需要配置环境变量 loadbalancer.client.name = 服务提供方
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        log.info("load balancer namespace -> {}", name);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }

	/*
	@Bean
	@LoadBalanced
	public RestOperations restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	*/
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}	
	
}
