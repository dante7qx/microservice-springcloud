package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaK8sApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaK8sApplication.class, args);
	}
}
