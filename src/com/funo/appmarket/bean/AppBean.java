package com.funo.appmarket.bean;

import java.io.Serializable;
import java.text.DecimalFormat;

import android.text.TextUtils;

public class AppBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long appId;// 应用ID
	private long partnerId;// 合作伙伴ID
	private String appLogo;// 应用LOGO
	private String appName;// 应用名称
	private String pkgname;// 应用包名
	private String downnum;// 下载次数
	private String appSubType;// 应用类型
	private String appPy;// 拼音首字母
	private String appInfo;// 内容摘要
	private String appIntro;// 应用介绍
	private String appImg1;// 应用图片1
	private String appImg2;// 应用图片2
	private String appImg3;// 应用图片3
	private String appImg4;// 应用图片4
	private String appImg5;// 应用图片5
	private float appSize;// 应用大小
	private String appVersion;// 当前版本
	private String tag;// 标签类型 0:无标签 1:精品 2:NEW 3:HOT
	private String url;// 下载地址
	private float score;// 评分
	private String updateTime;

	public AppBean() {
		
	}
	
	public AppBean(String appName, String appInfo) {
		this.appName = appName;
		this.appInfo = appInfo;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
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

	public String getPkgname() {
		return pkgname;
	}

	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	public String getDownnum() {
		if (!TextUtils.isEmpty(downnum)) {
			try {
				int download_num = Integer.parseInt(downnum);
				if (download_num > 10000) {
					return download_num / 10000.0 + "万";
				} else {
					return download_num + "次";
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return downnum;
	}

	public void setDownnum(String downnum) {
		this.downnum = downnum;
	}

	public String getAppSubType() {
		return appSubType;
	}

	public void setAppSubType(String appSubType) {
		this.appSubType = appSubType;
	}

	public String getAppPy() {
		return appPy;
	}

	public void setAppPy(String appPy) {
		this.appPy = appPy;
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

	public String getAppImg1() {
		return appImg1;
	}

	public void setAppImg1(String appImg1) {
		this.appImg1 = appImg1;
	}

	public String getAppImg2() {
		return appImg2;
	}

	public void setAppImg2(String appImg2) {
		this.appImg2 = appImg2;
	}

	public String getAppImg3() {
		return appImg3;
	}

	public void setAppImg3(String appImg3) {
		this.appImg3 = appImg3;
	}

	public String getAppImg4() {
		return appImg4;
	}

	public void setAppImg4(String appImg4) {
		this.appImg4 = appImg4;
	}

	public String getAppImg5() {
		return appImg5;
	}

	public void setAppImg5(String appImg5) {
		this.appImg5 = appImg5;
	}

	public float getAppSize() {
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		return Float.valueOf(decimalFormat.format(appSize / 1024));
	}

	public void setAppSize(float appSize) {
		this.appSize = appSize;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
