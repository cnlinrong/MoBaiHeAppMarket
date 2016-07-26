package com.funo.appmarket.app;

import org.xutils.DbManager;
import org.xutils.x;

import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;

public class MyApplication extends Application {

	private static MyApplication myApplication;

	private DbManager.DaoConfig daoConfig;
	private DbManager db;

	@Override
	public void onCreate() {
		super.onCreate();

		myApplication = this;

		CrashReport.initCrashReport(getApplicationContext(), "900037894", true);

		x.Ext.init(this);// xutils 初始化
		daoConfig = new DbManager.DaoConfig().setDbName("mobaihe_db")// 创建数据库的名称
				.setDbVersion(1)// 数据库版本号
				.setDbUpgradeListener(new DbManager.DbUpgradeListener() {

					@Override
					public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
						// TODO: ...
						// db.addColumn(...);
						// db.dropTable(...);
						// ...
					}

				});// 数据库更新操作
		db = x.getDb(daoConfig);
	}

	public static MyApplication getInstance() {
		return myApplication;
	}

	public DbManager.DaoConfig getDaoConfig() {
		return daoConfig;
	}

	public DbManager getDb() {
		return db;
	}
	
}
