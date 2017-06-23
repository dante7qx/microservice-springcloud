package org.dante.springcloud.microsleuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class MicroSleuth2Application {
	
	@Bean 
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroSleuth2Application.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/service2")
	public String hello() {
		String result = "service2 -> Hello, Service1!";
		String resp3 = restTemplate.getForObject("http://localhost:9903/service3", String.class);
		String resp4 = restTemplate.getForObject("http://localhost:9904/service4", String.class);
		return result + " <--> " + resp3 + " <--> " + resp4;
	}
}
