package org.dante.springcloud.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * Feign 上传文件
 * 
 * 参考：
 * https://github.com/OpenFeign/feign-form
 * 
 * 注意：
 * 1. produces 和 consumers 不能缺少
 * 2. 参数必须使用 @RequestPart 来接收
 * 
 * @author dante
 *
 */
@FeignClient(name = "micro-provider-user3", configuration = FileUploadFeignClient.MultipartSupportConfig.class)
public interface FileUploadFeignClient {
	
	@RequestMapping(value = "/upload4feign", method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String handleFileUpload(@RequestPart(value = "file4feign") MultipartFile file);
	
	public class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
