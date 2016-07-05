package com.funo.appmarket.app;

import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;

public class MyApplication extends Application {

	private static MyApplication myApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();

		myApplication = this;
		
		CrashReport.initCrashReport(getApplicationContext(), "900037894", true);
	}

	public static MyApplication getInstance() {
		return myApplication;
	}
	
}
