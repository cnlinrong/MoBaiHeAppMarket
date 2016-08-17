package com.funo.appmarket.activity;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppsViewPagerAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.SearchAppByTypeService;
import com.funo.appmarket.business.SearchAppByTypeService.SearchAppByTypeCallback;
import com.funo.appmarket.business.define.ISearchAppByTypeService.SearchAppByTypeParam;
import com.funo.appmarket.util.AnimationUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class AppsActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private SearchAppByTypeService searchAppByTypeService;
	
	private TextView btn_hot;
	private TextView btn_new;
	private TextView sub_parent_label;
	private View search;
	private TextView pager_bar;
	
	private ViewPager appsViewPager;
	private AppsViewPagerAdapter appsViewPagerAdapter;
	
	private String subParentLabel;
	private String subParentId;
	
	private int pageSize = 15;
	
	private boolean isHot = true;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchAppByTypeService = new SearchAppByTypeService(getContext());
		
		setContentView(R.layout.activity_apps);
		
		pager_bar = (TextView) findViewById(R.id.pager_bar);		
		sub_parent_label = (TextView) findViewById(R.id.sub_parent_label);
		
		Intent intent = getIntent();
		subParentLabel = intent.getStringExtra("subParentLabel");
		subParentId = intent.getStringExtra("subParentId");
		
		sub_parent_label.setText(subParentLabel);
		
		appsViewPager = (ViewPager) findViewById(R.id.appsViewPager);
		appsViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				appsViewPagerAdapter.resetOldView();
				appsViewPagerAdapter.resetGridViews(position);
				
				pager_bar.setText((position + 1) + "/" + appsViewPagerAdapter.getCount());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
			
		});
		
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
		
		btn_hot = (TextView) findViewById(R.id.btn_hot);
		btn_hot.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		btn_hot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!isHot) {
					isHot = true;
					
					btn_hot.setTextSize(22);
					btn_new.setTextSize(17);
					
					refreshData(0);
				}
			}
			
		});
		
		btn_new = (TextView) findViewById(R.id.btn_new);
		btn_new.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		btn_new.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isHot) {
					isHot = false;
					
					btn_hot.setTextSize(17);
					btn_new.setTextSize(22);
					
					refreshData(1);
				}
			}
			
		});
		
		refreshData(0);
		
		appsViewPager.post(new Runnable() {
			
			@Override
			public void run() {
				appsViewPager.requestFocus();
			}
			
		});
	}
	
	private void refreshData(final int orderType) {
		SearchAppByTypeParam searchAppByTypeParam = new SearchAppByTypeParam();
		searchAppByTypeParam.smallTypeId = subParentId;
		searchAppByTypeParam.orderType = orderType;
		searchAppByTypeParam.pageSize = pageSize;
		searchAppByTypeParam.currentPage = 1;
		searchAppByTypeService.searchAppByType(searchAppByTypeParam, new SearchAppByTypeCallback() {
			
			@Override
			public void doCallback(List<AppBean> appBeans, int pageCount) {
				if (appBeans != null) {
					appsViewPagerAdapter = new AppsViewPagerAdapter(getSupportFragmentManager(), getContext(), pageCount, orderType, subParentId);
					appsViewPager.setAdapter(appsViewPagerAdapter);
					if (pageCount > 0) {
						pager_bar.setText("1/" + pageCount);
					}
				}
			}
			
		});
	}
	
}
