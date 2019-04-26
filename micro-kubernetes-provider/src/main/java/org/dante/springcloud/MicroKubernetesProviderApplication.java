package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

/**
 * 参考：
 * 		https://www.baeldung.com/spring-cloud-kubernetes
 * 		https://cloud.tencent.com/developer/article/1339952
 * 
 * @author dante
 *
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {EurekaClientAutoConfiguration.class})
public class MicroKubernetesProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroKubernetesProviderApplication.class, args);
	}
}
