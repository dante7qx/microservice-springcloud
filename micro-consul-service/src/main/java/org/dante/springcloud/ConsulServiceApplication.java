package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration;

/**
 * 本例使用Consul作为注册中心，所以要排除 EurekaClientAutoConfiguration 和 EurekaDiscoveryClientConfiguration
 * 
 * @author dante
 *
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = { EurekaClientAutoConfiguration.class, EurekaDiscoveryClientConfiguration.class })
public class ConsulServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsulServiceApplication.class, args);
	}

}
