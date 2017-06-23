package org.dante.springcloud.microsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MicroSleuth3Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroSleuth3Application.class, args);
	}

	@GetMapping("/service3")
	public String hello() {
		return "service3 -> Hello, Service2!";
	}
}
