package org.dante.springcloud.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.dante.springcloud.prop.SecretProp;
import org.dante.springcloud.vo.MsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class HelloController {

	@Autowired
	private SecretProp secretProp;

	@GetMapping("/info")
	public MsgVO info() {
		return MsgVO.of(secretProp.getAuthor(), secretProp.getInfo());
	}

	@GetMapping("/host")
	public String host() throws UnknownHostException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Host: ").append(InetAddress.getLocalHost().getHostName()).append("<br/>");
		stringBuilder.append("IP: ").append(InetAddress.getLocalHost().getHostAddress()).append("<br/>");
		stringBuilder.append("Type: ").append("Msg Data Provider").append("<br/>");
		return stringBuilder.toString();
	}

}
