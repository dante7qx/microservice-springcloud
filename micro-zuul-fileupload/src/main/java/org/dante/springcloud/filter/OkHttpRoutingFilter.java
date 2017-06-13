package org.dante.springcloud.filter;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;

public class OkHttpRoutingFilter extends ZuulFilter {
	
	@Autowired
	private ProxyRequestHelper helper;


	@Override
	public boolean shouldFilter() {
		return RequestContext.getCurrentContext().getRouteHost() != null
				&& RequestContext.getCurrentContext().sendZuulResponse();
	}

	@Override
	public Object run() {
		OkHttpClient httpClient = new OkHttpClient.Builder().build();

		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		String method = request.getMethod();

		String uri = this.helper.buildZuulRequestURI(request);

		okhttp3.Headers.Builder headers = new okhttp3.Headers.Builder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			Enumeration<String> values = request.getHeaders(name);

			while (values.hasMoreElements()) {
				String value = values.nextElement();
				headers.add(name, value);
			}
		}
		try {
			InputStream inputStream = request.getInputStream();

			RequestBody requestBody = null;
			if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
				MediaType mediaType = null;
				if (headers.get("Content-Type") != null) {
					mediaType = MediaType.parse(headers.get("Content-Type"));
				}
				requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
			}

			Request.Builder builder = new Request.Builder()
					.headers(headers.build())
					.url(uri)
					.method(method, requestBody);

			Response response = httpClient.newCall(builder.build()).execute();

			LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();

			for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
				responseHeaders.put(entry.getKey(), entry.getValue());
			}

			this.helper.setResponse(response.code(), response.body().byteStream(),
					responseHeaders);
			context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "routing";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
