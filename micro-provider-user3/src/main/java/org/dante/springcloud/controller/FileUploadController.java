package org.dante.springcloud.controller;

import org.dante.springcloud.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
		String result = "ok";
		try {
			logger.info("Upload file: {}", file);
			uploadService.upload(file);
		} catch (Exception e) {
			logger.error("文件上传失败！", e);
			result = "wrong";
		}
		return result;
	}
}
