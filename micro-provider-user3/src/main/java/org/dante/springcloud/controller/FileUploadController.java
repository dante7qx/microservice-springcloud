package org.dante.springcloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.dante.springcloud.service.UploadService;
import org.dante.springcloud.vo.UploadVO;
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

	private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("redheadHistory") MultipartFile file, HttpServletRequest request) {
		String result = "ok";
		try {
			logger.info("Upload file: {}", file.getOriginalFilename());
			uploadService.upload(file);
		} catch (Exception e) {
			logger.error("文件上传失败！", e);
			result = "wrong";
		}
		return result;
	}
	
	@RequestMapping(value = "/upload2_Ax", method = RequestMethod.POST)
	public String upload2(UploadVO uploadVO, HttpServletRequest request) {
		String result = "ok";
		try {
			MultipartFile file = uploadVO.getRedheadHistory();
			String fileName = file.getOriginalFilename();
			logger.info("Upload file: {}", fileName);
			uploadService.upload(file);
		} catch (Exception e) {
			logger.error("文件上传失败！", e);
			result = "wrong";
		}
		return result;
	}
	
	@RequestMapping(value = "/bus", method = RequestMethod.POST)
	public String bus(UploadVO uploadVO) {
		String result = "ok";
		try {
			result = uploadVO.getTitle() + " - " + uploadVO.getSubject();
		} catch (Exception e) {
			logger.error("文件上传失败！", e);
			result = "wrong";
		}
		return result;
	}
}

// 文件乱码: http://qa.didispace.com/?/question/44
