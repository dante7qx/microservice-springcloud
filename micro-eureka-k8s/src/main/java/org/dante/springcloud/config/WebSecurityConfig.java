package org.dante.springcloud.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 5 默认开启 csrf 验证，若开启 Eureka 安全认证，需要关闭 csrf 验证。
 * 
 * @author dante
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/eureka/**");
		http.authorizeRequests().antMatchers("/actuator/health").permitAll();
		super.configure(http);
	}
}
