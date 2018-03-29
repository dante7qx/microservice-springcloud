package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientExcludeJerseyApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientExcludeJerseyApplication.class, args);
	}
}
