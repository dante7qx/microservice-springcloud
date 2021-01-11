package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 参考：http://www.mydlq.club/article/32/
 * 
 * @author dante
 *
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = { EurekaClientAutoConfiguration.class, EurekaDiscoveryClientConfiguration.class })
public class MicroKubernetesConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroKubernetesConsumerApplication.class, args);
	}
}
