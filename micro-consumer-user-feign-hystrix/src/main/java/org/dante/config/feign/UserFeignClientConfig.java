package org.dante.config.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.Logger;

/**
 * Feign默认的配置
 * 
 * Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)
 * Encoder feignEncoder: SpringEncoder
 * Logger feignLogger: Slf4jLogger
 * Contract feignContract: SpringMvcContract
 * Feign.Builder feignBuilder: HystrixFeign.Builder
 * Client feignClient: if Ribbon is enabled it is a LoadBalancerFeignClient, otherwise the default feign client is used.
 * 
 * @author dante
 *
 */
@Configuration
public class UserFeignClientConfig {
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	
	/**
	 * 如下配置会禁用UserFeignClient的Hystrix支持
	 * Feign
	 * 
	 * @return
	 */
//	@Bean
//	@Scope("prototype")
//	public Feign.Builder feignBuilder() {
//		return Feign.builder();
//	}
}
