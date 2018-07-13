package org.dante.springcloud.controller;

import org.dante.springcloud.prop.SpiritProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	// 配置无法自动热更新
	@Value("${spirit.version}")
	private String version;
	
	@Autowired
	private SpiritProp spiritProp;
	
	@GetMapping("/hello")
	public String hello() {
		return spiritProp.getVersion() + " - " + spiritProp.getMessage() + " - " + version;
	}
	
}
