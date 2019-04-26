package org.dante.springcloud.service;

import org.dante.springcloud.vo.MsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ConsumerService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackName", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
	public String getProviderHost() {
		String providerHost = restTemplate.getForObject("http://cloud-k8s-provider-service:8891/provider/host",
				String.class);
		return providerHost;
	}
	
	@SuppressWarnings("unused")
	private String getFallbackName() {
        return "Fallback";
    }

	public MsgVO getProviderInfo() {
		return restTemplate.getForObject("http://cloud-k8s-provider-service:8891/provider/info", MsgVO.class);
	}
}
