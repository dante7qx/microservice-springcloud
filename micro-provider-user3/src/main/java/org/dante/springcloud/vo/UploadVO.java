package org.dante.springcloud.vo;

import org.springframework.web.multipart.MultipartFile;

public class UploadVO {
	
	private String title;
	private String subject;
	
	private MultipartFile redheadHistory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MultipartFile getRedheadHistory() {
		return redheadHistory;
	}

	public void setRedheadHistory(MultipartFile redheadHistory) {
		this.redheadHistory = redheadHistory;
	}
	
}
