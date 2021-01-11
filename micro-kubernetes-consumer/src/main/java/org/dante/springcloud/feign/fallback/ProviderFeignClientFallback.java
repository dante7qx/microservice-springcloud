package org.dante.springcloud.feign.fallback;

import org.dante.springcloud.feign.ProviderFeginClient;
import org.dante.springcloud.vo.MsgVO;
import org.springframework.stereotype.Component;

@Component
public class ProviderFeignClientFallback implements ProviderFeginClient {

	private final MsgVO msgVO = new MsgVO("未知", "未知");
	
	@Override
	public String getProviderHost() {
		return "Provier 服务暂不可用，请稍后重试！";
	}

	@Override
	public MsgVO getProviderInfo() {
		return msgVO;
	}

}
