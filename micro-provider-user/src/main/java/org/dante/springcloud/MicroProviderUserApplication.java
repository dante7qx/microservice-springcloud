package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroProviderUserApplication.class, args);
	}
}
