package org.dante.springcloud.microsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class MicroSleuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroSleuthServerApplication.class, args);
	}

}
