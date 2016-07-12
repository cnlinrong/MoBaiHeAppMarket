package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.KeyboardGridViewAdapter;
import com.funo.appmarket.adapter.PopularAppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;

import android.os.Bundle;
import android.widget.GridView;

public class SearchActivity extends BaseActivity {

	private GridView keyboard;
	private GridView popular_apps;
	
	private KeyboardGridViewAdapter keyboardGridViewAdapter;
	private PopularAppsGridViewAdapter popularAppsGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search);
		
		keyboard = (GridView) findViewById(R.id.keyboard);
		popular_apps = (GridView) findViewById(R.id.popular_apps);
		
		keyboardGridViewAdapter = new KeyboardGridViewAdapter(getContext());
		keyboard.setAdapter(keyboardGridViewAdapter);
		
		AppBean appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		popularAppsGridViewAdapter = new PopularAppsGridViewAdapter(getContext(), appBeans);
		popular_apps.setAdapter(popularAppsGridViewAdapter);
	}

}
