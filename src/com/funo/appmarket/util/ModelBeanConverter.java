package com.funo.appmarket.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.model.AppModel;
import com.funo.appmarket.model.NavModel;

public class ModelBeanConverter {

	public static AppBean appModel2Bean(AppModel appModel) {
		AppBean appBean = null;
		if (appModel != null) {
			appBean = new AppBean();
			appBean.setAppId(appModel.getAppId());
			appBean.setPartnerId(appModel.getPartnerId());
			appBean.setAppLogo(appModel.getAppLogo());
			appBean.setAppName(appModel.getAppName());
			appBean.setPkgname(appModel.getPkgname());
			appBean.setDownnum(appModel.getDownnum());
			appBean.setAppSubType(appModel.getAppSubType());
			appBean.setAppPy(appModel.getAppPy());
			appBean.setAppInfo(appModel.getAppInfo());
			appBean.setAppIntro(appModel.getAppIntro());
			appBean.setAppImg1(appModel.getAppImg1());
			appBean.setAppImg2(appModel.getAppImg2());
			appBean.setAppImg3(appModel.getAppImg3());
			appBean.setAppImg4(appModel.getAppImg4());
			appBean.setAppImg5(appModel.getAppImg5());
			appBean.setAppSize(appModel.getAppSize());
			appBean.setAppVersion(appModel.getAppVersion());
			appBean.setTag(appModel.getTag());
			appBean.setUrl(appModel.getUrl());
			appBean.setScore(appModel.getScore());
			appBean.setUpdateTime(appModel.getUpdateTime());
		}
		return appBean;
	}
	
	public static AppModel appBean2Model(AppBean appBean) {
		AppModel appModel = null;
		if (appBean != null) {
			appModel = new AppModel();
			appModel.setAppId(appBean.getAppId());
			appModel.setPartnerId(appBean.getAppId());
			appModel.setAppLogo(appBean.getAppLogo());
			appModel.setAppName(appBean.getAppName());
			appModel.setPkgname(appBean.getPkgname());
			appModel.setDownnum(appBean.getDownnum());
			appModel.setAppSubType(appBean.getAppSubType());
			appModel.setAppPy(appBean.getAppPy());
			appModel.setAppInfo(appBean.getAppInfo());
			appModel.setAppIntro(appBean.getAppIntro());
			appModel.setAppImg1(appBean.getAppImg1());
			appModel.setAppImg2(appBean.getAppImg2());
			appModel.setAppImg3(appBean.getAppImg3());
			appModel.setAppImg4(appBean.getAppImg4());
			appModel.setAppImg5(appBean.getAppImg5());
			appModel.setAppSize(appBean.getAppSize());
			appModel.setAppVersion(appBean.getAppVersion());
			appModel.setTag(appBean.getTag());
			appModel.setUrl(appBean.getUrl());
			appModel.setScore(appBean.getScore());
			appModel.setUpdateTime(appBean.getUpdateTime());
			appModel.setInstalled_flag(false);
			appModel.setCreatedDate(new Date());
		}
		return appModel;
	}
	
	public static List<AppBean> appModels2Beans(List<AppModel> appModels) {
		List<AppBean> appBeans = null;
		if (appModels != null) {
			appBeans = new ArrayList<AppBean>();
			for (AppModel appModel : appModels) {
				appBeans.add(appModel2Bean(appModel));
			}
		}
		return appBeans;
	}
	
	public static List<AppModel> appBeans2Models(List<AppBean> appBeans) {
		List<AppModel> appModels = null;
		if (appBeans != null) {
			appModels = new ArrayList<AppModel>();
			for (AppBean appBean : appBeans) {
				appModels.add(appBean2Model(appBean));
			}
		}
		return appModels;
	}
	
	public static NavItem navModel2Bean(NavModel navModel) {
		NavItem navItem = null;
		if (navModel != null) {
			navItem = new NavItem();
			navItem.setLabel(navModel.getLabel());
			navItem.setValue(navModel.getValue());
			navItem.setLogo(navModel.getLogo());
			navItem.setUpdateTime(navModel.getUpdateTime());
		}
		return navItem;
	}
	
	public static NavModel navBean2Model(NavItem navItem, int parentId) {
		NavModel navModel = null;
		if (navItem != null) {
			navModel = new NavModel();
			navModel.setParentId(parentId);
			navModel.setLabel(navItem.getLabel());
			navModel.setLogo(navItem.getLogo());
			navModel.setValue(navItem.getValue());
			navModel.setUpdateTime(navItem.getUpdateTime());
		}
		return navModel;
	}
	
	public static List<NavItem> navModels2Beans(List<NavModel> navModels) {
		List<NavItem> navItems = null;
		if (navModels != null) {
			navItems = new ArrayList<NavItem>();
			for (NavModel navModel : navModels) {
				navItems.add(navModel2Bean(navModel));
			}
		}
		return navItems;
	}
	
	public static List<NavModel> navBeans2Models(List<NavItem> navItems, int parentId) {
		List<NavModel> navModels = null;
		if (navItems != null) {
			navModels = new ArrayList<NavModel>();
			for (NavItem navItem : navItems) {
				navModels.add(navBean2Model(navItem, parentId));
			}
		}
		return navModels;
	}
	
}
