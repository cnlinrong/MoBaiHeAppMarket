package com.funo.appmarket.db;

import java.util.List;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import com.funo.appmarket.app.MyApplication;
import com.funo.appmarket.model.AppInfo;
import com.tencent.bugly.crashreport.CrashReport;

public class AppInfoDB {

	public static DbManager getDbManager() {
		DbManager dbManager = MyApplication.getInstance().getDb();
		return dbManager;
	}

	/**
	 * 查询所有应用
	 * 
	 * @return
	 */
	public static List<AppInfo> getAllAppInfos() {
		List<AppInfo> appInfos = null;
		try {
			appInfos = getDbManager().selector(AppInfo.class).orderBy("createdDate", true).findAll();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appInfos;
	}

	/**
	 * 根据应用Id查询应用信息
	 * 
	 * @param appId
	 * 
	 * @return
	 */
	public static AppInfo getAppById(String appId) {
		AppInfo appInfo = null;
		try {
			List<AppInfo> appInfos = getDbManager().selector(AppInfo.class).where("appId", "=", appId).findAll();
			if (appInfos != null && !appInfos.isEmpty()) {
				appInfo = appInfos.get(0);
			}
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appInfo;
	}

	/**
	 * 保存应用信息
	 * 
	 * @param appInfo
	 */
	public static void saveAppInfo(AppInfo appInfo) {
		try {
			getDbManager().save(appInfo);
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 批量插入应用数据
	 * 
	 * @param appInfos
	 */
	public static void batchInsert(List<AppInfo> appInfos) {
		try {
			for (AppInfo appInfo : appInfos) {
				getDbManager().save(appInfo);
			}
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 将应用标记为已安装
	 * 
	 * @param packageName
	 */
	public static void markAppInstalled(String packageName) {
		try {
			getDbManager().update(AppInfo.class, WhereBuilder.b("packageName", "=", packageName),
					new KeyValue("installed_flag", true));
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	public void clearAppInfos() {
		try {
			getDbManager().delete(AppInfo.class);
		} catch (DbException e) {
			e.printStackTrace();
			
			CrashReport.postCatchedException(e);
		}
	}
	
}
