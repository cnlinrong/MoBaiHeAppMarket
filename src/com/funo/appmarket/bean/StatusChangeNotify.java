package com.funo.appmarket.bean;

public class StatusChangeNotify {

	private boolean isTypeChage;// 应用大类是否有更新，true or false
	private int templateUsedId;// 模板ID

	public boolean isTypeChage() {
		return isTypeChage;
	}

	public void setTypeChage(boolean isTypeChage) {
		this.isTypeChage = isTypeChage;
	}

	public int getTemplateUsedId() {
		return templateUsedId;
	}

	public void setTemplateUsedId(int templateUsedId) {
		this.templateUsedId = templateUsedId;
	}

}
