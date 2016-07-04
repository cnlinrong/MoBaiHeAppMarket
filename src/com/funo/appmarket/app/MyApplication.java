package com.funo.appmarket.app;

import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		CrashReport.initCrashReport(getApplicationContext(), "900037894", true);
	}

}
