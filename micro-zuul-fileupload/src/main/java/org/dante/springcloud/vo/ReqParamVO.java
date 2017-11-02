package org.dante.springcloud.vo;

public class ReqParamVO {

	private String paramStr;

	private String fileNameStr;

	public ReqParamVO(String paramStr, String fileNameStr) {
		this.paramStr = paramStr;
		this.fileNameStr = fileNameStr;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public String getFileNameStr() {
		return fileNameStr;
	}

	public void setFileNameStr(String fileNameStr) {
		this.fileNameStr = fileNameStr;
	}

	@Override
	public String toString() {
		return "ReqParamVO [paramStr=" + paramStr + ", fileNameStr=" + fileNameStr + "]";
	}

}
