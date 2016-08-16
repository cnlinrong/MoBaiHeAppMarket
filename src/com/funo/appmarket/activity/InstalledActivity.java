package com.funo.appmarket.activity;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.InstalledViewPagerAdapter;
import com.funo.appmarket.util.AnimationUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class InstalledActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private TextView pager_bar;
	private View search;
	
	private ViewPager installedViewPager;
	private InstalledViewPagerAdapter installedViewPagerAdapter;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_installed);
		
		pager_bar = (TextView) findViewById(R.id.pager_bar);
		
		installedViewPager = (ViewPager) findViewById(R.id.installedViewPager);
		installedViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				installedViewPagerAdapter.resetOldView();
				
				pager_bar.setText((position + 1) + "/" + installedViewPagerAdapter.getCount());
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
				startActivity(new Intent(getContext(), InstalledSearchActivity.class));
			}
			
		});
		
		refreshData();
		
		installedViewPager.post(new Runnable() {
			
			@Override
			public void run() {
				installedViewPager.requestFocus();
			}
			
		});
	}
	
	private void refreshData() {
		installedViewPagerAdapter = new InstalledViewPagerAdapter(getSupportFragmentManager(), getContext());
		if (installedViewPagerAdapter.getCount() > 0) {
			pager_bar.setText("1/" + installedViewPagerAdapter.getCount());
		}
		installedViewPager.setAdapter(installedViewPagerAdapter);
	}
	
}
