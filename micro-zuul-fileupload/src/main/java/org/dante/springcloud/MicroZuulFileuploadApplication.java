package org.dante.springcloud;

import org.dante.springcloud.filter.QueryParamPortPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class MicroZuulFileuploadApplication {
	
	@Bean
	public QueryParamPortPreFilter queryParamPortPreFilter() {
		return new QueryParamPortPreFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroZuulFileuploadApplication.class, args);
	}
}
