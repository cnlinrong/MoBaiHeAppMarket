package com.funo.appmarket.db;

import java.util.List;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import com.funo.appmarket.app.MyApplication;
import com.funo.appmarket.model.NavModel;
import com.tencent.bugly.crashreport.CrashReport;

public class NavModelDB {

	public static DbManager getDbManager() {
		DbManager dbManager = MyApplication.getInstance().getDb();
		return dbManager;
	}

	/**
	 * 根据父Id查询导航项
	 * 
	 * @param parentId
	 * 
	 * @return
	 */
	public static List<NavModel> getNavItemsByParentId(int parentId) {
		List<NavModel> navModels = null;
		try {
			navModels = getDbManager().selector(NavModel.class).where("parentId", "=", parentId).findAll();
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return navModels;
	}

	/**
	 * 查询所有导航项
	 * 
	 * @return
	 */
	public static List<NavModel> getAllNavItems() {
		List<NavModel> navModels = null;
		try {
			navModels = getDbManager().selector(NavModel.class).findAll();
		} catch (Exception e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
		return navModels;
	}

	/**
	 * 保存导航项信息
	 * 
	 * @param navModel
	 */
	public static void saveNavItem(NavModel navModel) {
		try {
			if (navModel != null) {
				getDbManager().save(navModel);
			}
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 批量插入导航项数据
	 * 
	 * @param navModels
	 */
	public static void batchInsertNavItems(List<NavModel> navModels) {
		try {
			if (navModels != null) {
				for (NavModel navModel : navModels) {
					getDbManager().save(navModel);
				}
			}
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

	/**
	 * 清楚所有导航项信息
	 */
	public static void clearAllNavItems() {
		try {
			getDbManager().delete(NavModel.class);
		} catch (DbException e) {
			e.printStackTrace();

			CrashReport.postCatchedException(e);
		}
	}

}
