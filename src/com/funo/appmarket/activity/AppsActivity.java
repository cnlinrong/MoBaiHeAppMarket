package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.AppService;
import com.funo.appmarket.business.AppService.RecAppInfoCallback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AppsActivity extends BaseActivity {

	private AppService appService;
	
	private GridView installed_list;
	private AppsGridViewAdapter installedGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apps);
		
		installed_list = (GridView) findViewById(R.id.installed_list);
		
		AppBean appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		
		installedGridViewAdapter = new AppsGridViewAdapter(getContext(), appBeans);
		installed_list.setAdapter(installedGridViewAdapter);
		installed_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(getContext(), AppDetailActivity.class));
			}
			
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		appService = new AppService(getContext());
		appService.recAppInfo(new RecAppInfoCallback() {
			
			@Override
			public void doCallback(List<AppBean> appData) {
				if (appData != null) {
					appBeans = appData;
					installedGridViewAdapter.setData(appData);
				}
			}
			
		});
	}
	
}
