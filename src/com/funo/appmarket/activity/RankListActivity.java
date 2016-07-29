package com.funo.appmarket.activity;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.RankListViewPagerAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.GetTopAppService;
import com.funo.appmarket.business.GetTopAppService.GetTopAppCallback;
import com.funo.appmarket.business.define.IGetTopAppService.GetTopAppParam;
import com.funo.appmarket.util.AnimationUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class RankListActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private GetTopAppService getTopAppService;
	
	private TextView btn_hot;
	private TextView btn_new;
	private View search;
	private TextView pager_bar;
	
	private ViewPager rankListViewPager;
	private RankListViewPagerAdapter rankListViewPagerAdapter;
	
	private int pageSize = 15;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getTopAppService = new GetTopAppService(getContext());
		
		setContentView(R.layout.activity_rank_list);
		
		pager_bar = (TextView) findViewById(R.id.pager_bar);
		
		rankListViewPager = (ViewPager) findViewById(R.id.rankListViewPager);
		rankListViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				pager_bar.setText((position + 1) + "/" + rankListViewPagerAdapter.getCount());
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
		
		refreshData(0);
		
		rankListViewPager.requestFocus();
	}

	private void refreshData(int orderType) {
		GetTopAppParam getTopAppParam = new GetTopAppParam();
		getTopAppParam.orderType = orderType;
		getTopAppParam.pageSize = pageSize;
		getTopAppParam.currentPage = 1;
		getTopAppService.getTopApp(getTopAppParam,  new GetTopAppCallback() {
			
			@Override
			public void doCallback(List<AppBean> appBeans, int pageCount) {
				if (appBeans != null) {
					rankListViewPagerAdapter = new RankListViewPagerAdapter(getSupportFragmentManager(), getContext(), pageCount, 0);
					rankListViewPager.setAdapter(rankListViewPagerAdapter);
					pager_bar.setText("1/" + rankListViewPagerAdapter.getCount());
				}
			}
			
		});
	}
	
}
