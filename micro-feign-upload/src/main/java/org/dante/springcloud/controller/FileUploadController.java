package org.dante.springcloud.controller;

import org.dante.springcloud.feignclient.FileUploadFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	
	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
		
	@Autowired
	private FileUploadFeignClient fileUploadFeignClient;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		String result = "ok";
		try {
			fileUploadFeignClient.handleFileUpload(file);
		} catch (Exception e) {
			logger.error("文件上传失败！", e);
			result = "wrong";
		}
		return result;
	}
}
