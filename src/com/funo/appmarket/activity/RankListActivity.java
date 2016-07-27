package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.RankListGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.GetTopAppService;
import com.funo.appmarket.business.GetTopAppService.GetTopAppCallback;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IGetTopAppService.GetTopAppParam;
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

public class RankListActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private GetTopAppService getTopAppService;
	
	private View search;
	private MainUpView mainUpView1;
	private View mOldView;
	
	private GridView installed_list;
	private RankListGridViewAdapter rankListGridViewAdapter;
	
	private List<AppBean> appData = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_rank_list);
		
		search = findViewById(R.id.search);
		search.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
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
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appData.add(appBean);
		
		rankListGridViewAdapter = new RankListGridViewAdapter(getContext(), appData);
		installed_list.setAdapter(rankListGridViewAdapter);
		installed_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getContext(), AppDetailActivity.class);
				intent.putExtra("selectedApp", rankListGridViewAdapter.getItem(position));
				startActivity(intent);
			}
			
		});
		
		getTopAppService = new GetTopAppService(getContext());
		GetTopAppParam getTopAppParam = new GetTopAppParam();
		getTopAppParam.orderType = 0;
		getTopAppParam.pageSize = BaseService.PAGE_SIZE;
		getTopAppParam.currentPage = 1;
		getTopAppService.getTopApp(getTopAppParam,  new GetTopAppCallback() {
			
			@Override
			public void doCallback(List<AppBean> appBeans) {
				if (appBeans != null) {
					appData = appBeans;
					rankListGridViewAdapter.setData(appBeans);
				}
			}
			
		});
		
		installed_list.post(new Runnable() {

			@Override
			public void run() {
				installed_list.requestFocus();
			}

		});
	}

}
