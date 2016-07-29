package com.funo.appmarket.db;

import java.util.List;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import com.funo.appmarket.app.MyApplication;
import com.funo.appmarket.model.AppModel;
import com.tencent.bugly.crashreport.CrashReport;

public class AppModelDB {

	public static DbManager getDbManager() {
		DbManager dbManager = MyApplication.getInstance().getDb();
		return dbManager;
	}

	/**
	 * 查询所有已安装应用
	 * 
	 * @return
	 */
	public static List<AppModel> getAllInstalledApps() {
		List<AppModel> appModels = null;
		try {
			appModels = getDbManager().selector(AppModel.class).where("installed_flag", "=", true)
					.orderBy("createdDate", true).findAll();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appModels;
	}

	/**
	 * 分页查询所有已安装应用
	 * 
	 * @param pageNo
	 * @param pageSize
	 * 
	 * @return
	 */
	public static List<AppModel> getAllInstalledApps(int pageNo, int pageSize) {
		List<AppModel> appModels = null;
		try {
			appModels = getDbManager().selector(AppModel.class).where("installed_flag", "=", true)
					.orderBy("createdDate", true).limit(pageSize).offset((pageNo - 1) * pageSize).findAll();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appModels;
	}

	/**
	 * 获取已安装应用的总页数
	 * 
	 * @param pageSize
	 * 
	 * @return
	 */
	public static int getInstalledAppsPageCount(int pageSize) {
		List<AppModel> appModels = getAllInstalledApps();
		return (int) Math.ceil((float) appModels.size() / pageSize);
	}
	
	/**
	 * 查询所有应用
	 * 
	 * @return
	 */
	public static List<AppModel> getAllApps() {
		List<AppModel> appModels = null;
		try {
			appModels = getDbManager().selector(AppModel.class).orderBy("createdDate", true).findAll();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appModels;
	}

	/**
	 * 根据应用包名查询应用信息
	 * 
	 * @param packageName
	 * 
	 * @return
	 */
	public static AppModel getAppByPackageName(String packageName) {
		AppModel appModel = null;
		try {
			List<AppModel> appModels = getDbManager().selector(AppModel.class).where("pkgname", "=", packageName)
					.findAll();
			if (appModels != null && !appModels.isEmpty()) {
				appModel = appModels.get(0);
			}
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appModel;
	}

	/**
	 * 根据应用拼音查询应用信息
	 * 
	 * @param pinyin
	 * 
	 * @return
	 */
	public static List<AppModel> findAppsByPinyin(String pinyin) {
		List<AppModel> appModels = null;
		try {
			appModels = getDbManager().selector(AppModel.class).where("appPy", "like", "%" + pinyin + "%").findAll();
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return appModels;
	}

	/**
	 * 保存应用信息
	 * 
	 * @param appModel
	 */
	public static void saveApp(AppModel appModel) {
		try {
			AppModel model = getAppByPackageName(appModel.getPkgname());
			if (model != null) {
				appModel.setId(model.getId());
			}
			getDbManager().saveOrUpdate(appModel);
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
	public static void batchInsertApps(List<AppModel> appModels) {
		try {
			for (AppModel appModel : appModels) {
				getDbManager().save(appModel);
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
			getDbManager().update(AppModel.class, WhereBuilder.b("pkgname", "=", packageName),
					new KeyValue("installed_flag", true));
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 删除应用信息
	 * 
	 * @param packageName
	 */
	public static void deleteApp(String packageName) {
		try {
			getDbManager().delete(AppModel.class, WhereBuilder.b("pkgname", "=", packageName));
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 清除未安装的应用信息
	 */
	public static void clearUninstalledApps() {
		try {
			getDbManager().delete(AppModel.class, WhereBuilder.b("installed_flag", "=", false));
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 清楚所有应用信息
	 */
	public static void clearAllApps() {
		try {
			getDbManager().delete(AppModel.class);
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

}
