package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroConsumerUserFeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroConsumerUserFeignApplication.class, args);
	}
}
