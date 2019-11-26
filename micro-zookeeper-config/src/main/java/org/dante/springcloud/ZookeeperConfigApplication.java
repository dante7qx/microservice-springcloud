package org.dante.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

/**
 * 参考：
 * https://blog.csdn.net/yuan1991926/article/details/79658015
 * http://www.glmapper.com/2019/01/06/springcloud-config-zk-project/
 * 
 * 现在Zookeeper中添加znode
 * 
 * create /config
 * create /config/zk
 * create /config/zk/micro-zookeeper-config,dev
 * create /config/zk/micro-zookeeper-config,dev/app.name "Hello 世界"
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
