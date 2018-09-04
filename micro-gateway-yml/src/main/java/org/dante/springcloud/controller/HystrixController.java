package org.dante.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
	
	@GetMapping("/fallback")
	public String fallback() {
		return "熔断回调方法！";
	}
	
}
