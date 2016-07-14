package com.funo.appmarket.bean;

public class AppBean {

	private long appId;
	private String appInfo;
	private String appIntro;
	private String appLogo;
	private String appName;
	private double appSize;
	private String appVersion;
	private long partnerId;
	private int score;
	private String tag;
	private String updateTime;

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public String getAppIntro() {
		return appIntro;
	}

	public void setAppIntro(String appIntro) {
		this.appIntro = appIntro;
	}

	public String getAppLogo() {
		return appLogo;
	}

	public void setAppLogo(String appLogo) {
		this.appLogo = appLogo;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public double getAppSize() {
		return appSize;
	}

	public void setAppSize(double appSize) {
		this.appSize = appSize;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public AppBean(String appName, String appInfo) {
		this.appName = appName;
		this.appInfo = appInfo;
	}

}
