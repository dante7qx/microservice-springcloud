package org.dante.springcloud.microsleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class MicroSleuth1Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroSleuth1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MicroSleuth1Application.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Tracer tracer;
	

	@GetMapping("/start")
	public String invoke() {
		LOGGER.info("开始服务调用，/start");
		return restTemplate.getForObject("http://localhost:9902/service2", String.class);
	}
	
	@GetMapping("/readtimeout")
	public String readtimeout() throws Exception {
		Thread.sleep(6000L);
		throw new Exception("error");
	}
	
	@GetMapping("/test")
	public String test() {
		doSomething();
		return "ok";
	}

	private void doSomething() {
		Span span = null;
		try {
			span = tracer.createSpan("sleuth-test");
			tracer.addTag("spirit", "test");
			Thread.sleep(3000L);
			span.logEvent("开始睡眠💤");
			Thread.sleep(2000L);
			span.logEvent("睡醒了😊");
		} catch (Exception e) {
			
		} finally {
			tracer.close(span);
		}
		
	}
	
	
}
