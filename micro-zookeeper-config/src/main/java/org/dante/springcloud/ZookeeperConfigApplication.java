package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

/**
 * 参考：
 * https://blog.csdn.net/yuan1991926/article/details/79658015
 * http://www.glmapper.com/2019/01/06/springcloud-config-zk-project/
 * 
 * @author dante
 *
 */
@SpringBootApplication(exclude = { EurekaClientAutoConfiguration.class })
public class ZookeeperConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZookeeperConfigApplication.class, args);
	}
}
