package com.funo.appmarket.util;

import java.io.File;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class InstallUtils {

	/**
	 * 判断手机是否拥有Root权限。
	 * 
	 * @return 有root权限返回true，否则返回false。
	 */
	public static boolean isRoot() {
		boolean bool = false;
		try {
			bool = (!new File("/system/bin/su").exists()) || (!new File("/system/xbin/su").exists());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 判断应用是否已经安装
	 * 
	 * @param context
	 * @param packageName
	 * 
	 * @return
	 */
	public static boolean hasInstalled(Context context, String packageName) {
		boolean hasInstalled = false;
		try {
			ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
			if (applicationInfo != null) {
				hasInstalled = true;
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return hasInstalled;
	}
	
}
