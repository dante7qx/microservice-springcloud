package org.dante.springcloud.controller;

import java.net.UnknownHostException;
import java.util.List;

import org.dante.springcloud.prop.ConfigMapProp;
import org.dante.springcloud.service.ConsumerService;
import org.dante.springcloud.vo.MsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

	@Autowired
	private ConfigMapProp configMapProp;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/info")
	public MsgVO info() {
		return consumerService.getProviderInfo();
	}

	@GetMapping("/host")
	public String host() throws UnknownHostException {
		log.info("ConfigMap => {}", configMapProp);
		String providerMsg = consumerService.getProviderHost();
		StringBuilder sb = new StringBuilder(configMapProp.getInfo());
		sb.append("<br>").append(providerMsg);
		return sb.toString();
	}

	@GetMapping("/svc_list")
	public String svcList() throws UnknownHostException {
		StringBuilder serviceList = new StringBuilder();
		if (discoveryClient != null) {
			List<String> services = this.discoveryClient.getServices();
			for (String service : services) {
				List<ServiceInstance> instances = this.discoveryClient.getInstances(service);
				serviceList.append(("[" + service + " : "
						+ ((!CollectionUtils.isEmpty(instances)) ? instances.size() : 0) + " instances ]"));
			}
		}
		return serviceList.toString();
	}

}
