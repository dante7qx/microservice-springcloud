package org.dante.springcloud.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "taitan")
public class ConfigMapProp {
	private String info = "惊奇队长 - 在座的都是垃圾";
}
