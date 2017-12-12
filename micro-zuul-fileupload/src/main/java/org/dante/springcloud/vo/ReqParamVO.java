package org.dante.springcloud.vo;

public class ReqParamVO {
	private String paramStr;

	private String valStr;
	
	private String fileNameStr;

	public ReqParamVO(String paramStr, String valStr, String fileNameStr) {
		this.paramStr = paramStr;
		this.valStr = valStr;
		this.fileNameStr = fileNameStr;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public String getValStr() {
		return valStr;
	}

	public void setValStr(String valStr) {
		this.valStr = valStr;
	}

	public String getFileNameStr() {
		return fileNameStr;
	}

	public void setFileNameStr(String fileNameStr) {
		this.fileNameStr = fileNameStr;
	}

	@Override
	public String toString() {
		return "ReqParamVO [paramStr=" + paramStr + ", valStr=" + valStr + ", fileNameStr=" + fileNameStr + "]";
	}

}
