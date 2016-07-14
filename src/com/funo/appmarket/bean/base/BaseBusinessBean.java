package com.funo.appmarket.bean.base;

import java.util.List;

public abstract class BaseBusinessBean<T> {

	private String code;
	private int dataCounts;
	private String msg;
	private List<T> responseData;
	private String time;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDataCounts() {
		return dataCounts;
	}

	public void setDataCounts(int dataCounts) {
		this.dataCounts = dataCounts;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<T> responseData) {
		this.responseData = responseData;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
