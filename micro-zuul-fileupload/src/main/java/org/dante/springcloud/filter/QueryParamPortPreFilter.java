package org.dante.springcloud.filter;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author Spencer Gibb
 */
public class QueryParamPortPreFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryParamPortPreFilter.class);

	public int filterOrder() {
		// run after PreDecorationFilter
		return 5 + 1;
	}

	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = getCurrentContext();
		String port = ctx.getRequest().getParameter("port");
		LOGGER.info("QueryParamPortPreFilter request port {}", port);
		return port != null;
	}

	public Object run() {
		RequestContext ctx = getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// put the serviceId in `RequestContext`
		String port = request.getParameter("port");
		try {
			LOGGER.info("request port is {}", port);
			URL url = UriComponentsBuilder.fromUri(ctx.getRouteHost().toURI())
					.port(new Integer(port))
					.build().toUri().toURL();
			ctx.setRouteHost(url);
		} catch (Exception e) {
			HttpServletResponse response = ctx.getResponse();
			try {
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.write("<h2>您的访问不合法，请检查！</h2>");
				pw.flush();
				pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		//	ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}
}
