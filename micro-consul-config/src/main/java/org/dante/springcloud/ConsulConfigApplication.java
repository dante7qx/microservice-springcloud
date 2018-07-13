package org.dante.springcloud;

import org.dante.springcloud.prop.SpiritProp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

@EnableDiscoveryClient
@EnableConfigurationProperties(SpiritProp.class)
@SpringBootApplication(exclude = { EurekaClientAutoConfiguration.class })
public class ConsulConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConfigApplication.class, args);
	}

}
