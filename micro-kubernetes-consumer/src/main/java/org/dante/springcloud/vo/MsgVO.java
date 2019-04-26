package org.dante.springcloud.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String author;
	private String info;
	
	public MsgVO() {
	}
	
	public static MsgVO of(String author, String info) {
		return new MsgVO(author, info);
	}
}
