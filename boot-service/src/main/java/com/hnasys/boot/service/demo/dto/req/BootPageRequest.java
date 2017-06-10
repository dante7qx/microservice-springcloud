package com.hnasys.boot.service.demo.dto.req;

public class BootPageRequest extends org.springframework.data.domain.PageRequest {

	private static final long serialVersionUID = 169531551067320271L;
	private static final int pageSize = 20;
	
	public BootPageRequest(int page, int size) {
		super(page, size);		
	}

}
