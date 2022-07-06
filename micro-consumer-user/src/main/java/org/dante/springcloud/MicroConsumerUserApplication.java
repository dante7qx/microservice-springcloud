package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Load Balancer 使用说明
 * 	https://piotrminkowski.com/2020/05/13/a-deep-dive-into-spring-cloud-load-balancer/
 * 	https://blog.csdn.net/abu935009066/article/details/112296435
 * 
 * @author dante
 *
 */
@SpringBootApplication
public class MicroConsumerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroConsumerUserApplication.class, args);
	}
}
