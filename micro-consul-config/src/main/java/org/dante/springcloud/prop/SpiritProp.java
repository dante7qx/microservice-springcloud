package org.dante.springcloud.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="spirit")
public class SpiritProp {
	
	private String version;
	private String message;
}
