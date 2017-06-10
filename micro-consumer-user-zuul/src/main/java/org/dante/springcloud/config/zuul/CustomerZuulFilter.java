package org.dante.springcloud.config.zuul;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class CustomerZuulFilter extends ZuulFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerZuulFilter.class);
	
	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String remoteHost = request.getRemoteHost();
		logger.info("Request remoteHost: {}", remoteHost);
		return null;
	}

	/**
	 * filter是否需要被运行
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * filter执行的优先级：从小到大
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * "pre", "routing", and "post"
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
