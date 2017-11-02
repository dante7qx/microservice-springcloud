package org.dante.springcloud;

import org.dante.springcloud.filter.QueryParamPortPreFilter;
import org.dante.springcloud.servletfilter.CacheInputStreamFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class MicroZuulFileuploadApplication {
	
	@Bean
	public QueryParamPortPreFilter queryParamPortPreFilter() {
		return new QueryParamPortPreFilter();
	}
	
	@Bean
    public FilterRegistrationBean signValidateFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CacheInputStreamFilter cacheInputStreamFilter = new CacheInputStreamFilter();
        registration.setFilter(cacheInputStreamFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(-11);
        return registration;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MicroZuulFileuploadApplication.class, args);
	}
}
