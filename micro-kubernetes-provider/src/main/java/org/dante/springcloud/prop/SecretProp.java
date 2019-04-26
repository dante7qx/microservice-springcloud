package org.dante.springcloud.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spirit")
public class SecretProp {
	private String author;
	private String info;
}
