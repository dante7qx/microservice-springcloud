package org.dante.springcloud.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * Zuul 的 Hystrix Fallback
 * 
 * @author dante
 *
 */
@Component
public class ZuulFallbackProvider implements FallbackProvider {

	@Override
	public String getRoute() {
		return "micro-provider-user3";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public InputStream getBody() throws IOException {
				String returnStr = new String("<h3>系统错误，请联系系统管理员！</h3>");
				InputStream ins = new ByteArrayInputStream(returnStr.getBytes());
				return ins;
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_HTML);
				headers.setAcceptCharset(Lists.newArrayList(Charset.forName("UTF-8")));
				return headers;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}

			@Override
			public void close() {

			}
		};
	}

}
