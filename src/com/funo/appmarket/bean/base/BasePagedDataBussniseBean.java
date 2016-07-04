package com.funo.appmarket.bean.base;

public class BasePagedDataBussniseBean<T> extends BaseBusinessBean {

	private BasePagedData<T> data;

	public BasePagedData<T> getData() {
		return data;
	}

	public void setData(BasePagedData<T> data) {
		this.data = data;
	}

}
