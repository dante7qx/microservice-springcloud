package com.hnasys.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}
	
	/*
	@Bean
	public FilterRegistrationBean registFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new Filter(){
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
            	System.out.println("=================");
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("in filter");
                chain.doFilter(request, response);
            }

            @Override
            public void destroy() {
            }
            
        });
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
	}
	*/
}
