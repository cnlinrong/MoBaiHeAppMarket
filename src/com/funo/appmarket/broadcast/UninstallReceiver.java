package com.funo.appmarket.broadcast;

import com.funo.appmarket.db.AppModelDB;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UninstallReceiver extends BroadcastReceiver {

	private static final int PACKAGE_NAME_START_INDEX = 8;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent == null) {
			return;
		}
		if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
			String data = intent.getDataString();
			if (data == null || data.length() <= PACKAGE_NAME_START_INDEX) {
				return;
			}
			String packageName = data.substring(PACKAGE_NAME_START_INDEX);
			AppModelDB.deleteApp(packageName);
		}
	}

}
