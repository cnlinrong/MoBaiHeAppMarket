package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.RecAppInfoService;
import com.funo.appmarket.business.RecAppInfoService.RecAppInfoCallback;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IRecAppInfoService.RecAppInfoReqParam;
import com.funo.appmarket.util.AnimationUtils;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;

public class InstalledActivity extends BaseActivity {

	private RecAppInfoService appService;
	
	private View search;
	private MainUpView mainUpView1;
	private View mOldView;
	
	private GridView installed_list;
	private AppsGridViewAdapter installedGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_installed);
		
		search = findViewById(R.id.search);
		search.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, 1.0f, 1.0f, 1.5f, 1.5f, 200);
				} else {
					AnimationUtils.scaleAnim(v, 1.5f, 1.5f, 1.0f, 1.0f, 200);
				}
			}
			
		});
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getContext(), SearchActivity.class));
			}
			
		});
		
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
        effectNoDrawBridge.setTranDurAnimTime(200);
        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
        mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(2);
		
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
					mainUpView1.setFocusView(view, mOldView, 1.1f);
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
				Intent intent = new Intent(getContext(), AppDetailActivity.class);
				intent.putExtra("selectedApp", installedGridViewAdapter.getItem(position));
				startActivity(intent);
			}
			
		});
		
		installed_list.post(new Runnable() {

			@Override
			public void run() {
				installed_list.requestFocus();
			}

		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		appService = new RecAppInfoService(getContext());
		RecAppInfoReqParam recAppInfoReqParam = new RecAppInfoReqParam();
		recAppInfoReqParam.type = 0;
		recAppInfoReqParam.pageSize = BaseService.PAGE_SIZE;
		recAppInfoReqParam.currentPage = 1;
		appService.recAppInfo(recAppInfoReqParam, new RecAppInfoCallback() {
			
			@Override
			public void doCallback(List<AppBean> appData) {
				if (appData != null) {
					appBeans = appData;
//					installedGridViewAdapter.setData(appData);
				}
			}
			
		});
	}
	
}
