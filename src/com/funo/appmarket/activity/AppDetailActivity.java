package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppImgsViewPagerAdapter;
import com.funo.appmarket.util.ToastUtils;
import com.funo.appmarket.view.SliderIndicatorBarView;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class AppDetailActivity extends BaseActivity implements OnClickListener {

	private View root_view;
	private Button btn_start;
	private Button btn_rate;
	private Button btn_uninstall;
	
	private SliderIndicatorBarView sliderIndicatorBarView;
	
	private ViewPager app_imgs;
	private AppImgsViewPagerAdapter appImgsViewPagerAdapter;
	
	private PopupWindow popupWindow;
	
	private List<String> appImgs = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_app_detail);
		
		sliderIndicatorBarView = (SliderIndicatorBarView) findViewById(R.id.slider_bar);
		
		root_view = findViewById(R.id.root_view);
		
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_rate = (Button) findViewById(R.id.btn_rate);
		btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
		
		btn_start.setOnClickListener(this);
		btn_rate.setOnClickListener(this);
		btn_uninstall.setOnClickListener(this);
		
		app_imgs = (ViewPager) findViewById(R.id.app_imgs);
		appImgs.add("https://www.baidu.com/img/bd_logo1.png");
		appImgs.add("https://img3.doubanio.com/icon/ul63839584-4.jpg");
		appImgs.add("https://img1.doubanio.com/icon/ul47183650-28.jpg");
		appImgs.add("https://img3.doubanio.com/icon/ul79926360-73.jpg");
		appImgs.add("https://www.baidu.com/img/bd_logo1.png");
		appImgsViewPagerAdapter = new AppImgsViewPagerAdapter(getSupportFragmentManager(), getContext(), appImgs);
		app_imgs.setAdapter(appImgsViewPagerAdapter);
		app_imgs.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				sliderIndicatorBarView.selectIndicator(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
			
		});
		
		sliderIndicatorBarView.setNum(appImgs.size());
		
		root_view.post(new Runnable() {
			
			@Override
			public void run() {
				View rootView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_rate, null);
				popupWindow = new PopupWindow(rootView, LayoutParams.MATCH_PARENT, root_view.getHeight() / 2, true);
				popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.setOutsideTouchable(true);
			}
			
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			String str = SystemProperties.get("ro.product.model", "");
			ToastUtils.showShortToast(getContext(), str);
			break;
		case R.id.btn_rate:
			if (popupWindow != null && !popupWindow.isShowing()) {
				popupWindow.showAtLocation(root_view, Gravity.BOTTOM, 0, 0);
			}
			break;
		case R.id.btn_uninstall:
			String str1 = SystemProperties.get("ro.product.stb.serialnum", "");
			ToastUtils.showShortToast(getContext(), str1);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			return;
		}
		super.onBackPressed();
	}
	
}
