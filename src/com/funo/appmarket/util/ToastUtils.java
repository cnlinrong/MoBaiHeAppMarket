package com.funo.appmarket.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	private static Context mContext;
	
	private static Toast toast;
	
	public static void showSameShortToast(Context context, String content) {
		if (mContext != context) {
			mContext = context;
			toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
		} else {
			toast.setText(content);
		}
		toast.show();
	}
	
	public static void showSameLongToast(Context context, String content) {
		if (mContext != context) {
			mContext = context;
			toast = Toast.makeText(mContext, content, Toast.LENGTH_LONG);
		} else {
			toast.setText(content);
		}
		toast.show();
	}
	
	public static void showShortToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLongToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_LONG).show();
	}
	
}
