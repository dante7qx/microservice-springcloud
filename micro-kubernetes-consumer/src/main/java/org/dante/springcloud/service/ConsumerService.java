package org.dante.springcloud.service;

import org.dante.springcloud.feign.ProviderFeginClient;
import org.dante.springcloud.vo.MsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	@Autowired
	private ProviderFeginClient providerFeginClient;

	public String getProviderHost() {
		return providerFeginClient.getProviderHost();
	}

	public MsgVO getProviderInfo() {
		return providerFeginClient.getProviderInfo();
	}
}
