package com.funo.appmarket.bean;

public class AppBean {

	private String appName;
	private String appImgUrl;
	private int rate;
	private int installStatus;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppImgUrl() {
		return appImgUrl;
	}

	public void setAppImgUrl(String appImgUrl) {
		this.appImgUrl = appImgUrl;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(int installStatus) {
		this.installStatus = installStatus;
	}

	public AppBean(String appName, String appImgUrl, int rate, int installStatus) {
		this.appName = appName;
		this.appImgUrl = appImgUrl;
		this.rate = rate;
		this.installStatus = installStatus;
	}

}
