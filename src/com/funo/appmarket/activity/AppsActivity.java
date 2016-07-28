package com.funo.appmarket.activity;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppsViewPagerAdapter;
import com.funo.appmarket.util.AnimationUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
	
	private TextView btn_hot;
	private TextView btn_new;
	
	private TextView sub_parent_label;
	private View search;
	
	private ViewPager appsViewPager;
	private AppsViewPagerAdapter appsViewPagerAdapter;
	
	private String subParentLabel;
	private String subParentId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_apps);
		
		sub_parent_label = (TextView) findViewById(R.id.sub_parent_label);
		
		Intent intent = getIntent();
		subParentLabel = intent.getStringExtra("subParentLabel");
		subParentId = intent.getStringExtra("subParentId");
		
		sub_parent_label.setText(subParentLabel);
		
		appsViewPager = (ViewPager) findViewById(R.id.appsViewPager);
		appsViewPagerAdapter = new AppsViewPagerAdapter(getSupportFragmentManager(), getContext(), 2, 0, subParentId);
		appsViewPager.setAdapter(appsViewPagerAdapter);
		
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
				btn_hot.setTextSize(22);
				btn_new.setTextSize(17);
				
				refreshData(0);
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
				btn_hot.setTextSize(17);
				btn_new.setTextSize(22);
				
				refreshData(1);
			}
			
		});
	}

	/**
	 * @param orderType 根据最热或最新排序 0:最热 1:最新
	 */
	private void refreshData(int orderType) {
		
	}
	
}
