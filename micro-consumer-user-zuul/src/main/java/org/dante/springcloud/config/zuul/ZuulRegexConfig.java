package org.dante.springcloud.config.zuul;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过正则配置 serviceId 和 zuul 的约定 (只支持服务发现)
 * 
 * serviceId: myusers-v1
 * route: /v1/myusers/**
 * 
 * @author dante
 *
 */
//@Configuration
public class ZuulRegexConfig {
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
	}
}
