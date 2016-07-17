package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.AppService;
import com.funo.appmarket.business.AppService.RecAppInfoCallback;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;

public class AppsActivity extends BaseActivity {

	private AppService appService;
	
	private MainUpView mainUpView1;
	private View mOldView;
	
	private GridView installed_list;
	private AppsGridViewAdapter installedGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apps);
		
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
        effectNoDrawBridge.setTranDurAnimTime(200);
        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距.
		
		installed_list = (GridView) findViewById(R.id.installed_list);
		installed_list.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * 这里注意要加判断是否为NULL.
				 * 因为在重新加载数据以后会出问题.
				 */
				if (view != null) {
					view.bringToFront();
					mainUpView1.setFocusView(view, mOldView, 1.2f);
				}
				mOldView = view;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
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
