package org.dante.springcloud.microsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MicroSleuth4Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroSleuth4Application.class, args);
	}

	@GetMapping("/service4")
	public String hello() {
		return "service4 -> Hello, Service2!";
	}
}
