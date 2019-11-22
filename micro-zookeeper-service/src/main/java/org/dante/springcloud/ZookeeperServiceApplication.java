package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

@EnableDiscoveryClient
@SpringBootApplication(exclude = { EurekaClientAutoConfiguration.class })
public class ZookeeperServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZookeeperServiceApplication.class, args);
	}
}
