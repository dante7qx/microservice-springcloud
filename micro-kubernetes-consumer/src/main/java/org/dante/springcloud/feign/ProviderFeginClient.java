package org.dante.springcloud.feign;

import org.dante.springcloud.feign.fallback.ProviderFeignClientFallback;
import org.dante.springcloud.vo.MsgVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cloud-k8s-provider-service", fallback = ProviderFeignClientFallback.class)
@RequestMapping("/provider")
public interface ProviderFeginClient {
	
	@GetMapping("/host")
	public String getProviderHost();
	
	@GetMapping("/info")
	public MsgVO getProviderInfo();
}
