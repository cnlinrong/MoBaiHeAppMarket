package com.funo.appmarket.activity.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

	private Context mContext;
	
	protected SharedPreferences sys_sp;// 系统信息数据库
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		sys_sp = getSharedPreferences("sys", Context.MODE_PRIVATE);
	}

	protected Context getContext() {
		return this.mContext;
	}
	
}
