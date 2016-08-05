package com.funo.appmarket.util;

import com.funo.appmarket.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
	
	 public static void commonTips2(Context mContext, String tips) {
	    	Toast toast = new Toast(mContext);
	    	toast.setGravity(Gravity.CENTER, 0, 0);
	    	toast.setDuration(Toast.LENGTH_SHORT);
	    	View view = LayoutInflater.from(mContext).inflate(R.layout.common_info_tips, null);
	    	TextView tv = (TextView) view.findViewById(R.id.common_tips);
	    	LinearLayout bg_tips_ll = (LinearLayout)view.findViewById(R.id.bg_tips_ll);
	        bg_tips_ll.setBackgroundResource(R.drawable.info_box);
	    	tv.setBackgroundResource(R.drawable.jqqd_allpage);
	    	toast.setView(view);
	    	toast.show();
	    	// commonTimes(mContext,tips);
	    }

	
}
