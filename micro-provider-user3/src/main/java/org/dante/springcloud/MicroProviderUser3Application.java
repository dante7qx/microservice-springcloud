package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroProviderUser3Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroProviderUser3Application.class, args);
	}
}
