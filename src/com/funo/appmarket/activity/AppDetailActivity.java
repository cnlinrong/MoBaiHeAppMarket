package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppImgsViewPagerAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.AppScoreUpdateService;
import com.funo.appmarket.business.AppScoreUpdateService.AppScoreUpdateCallback;
import com.funo.appmarket.business.define.IAppScoreUpdateService.AppScoreUpdateParam;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.util.ToastUtils;
import com.funo.appmarket.view.RatingBarView;
import com.funo.appmarket.view.RatingBarView.RatingCallback;
import com.funo.appmarket.view.RatingView;
import com.funo.appmarket.view.SliderIndicatorBarView;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class AppDetailActivity extends BaseActivity implements OnClickListener {

	private AppScoreUpdateService appScoreUpdateService;
	
	private View root_view;
	private Button btn_start;
	private Button btn_rate;
	private Button btn_uninstall;
	
	private ImageView app_logo;
	private TextView app_name;
	private TextView download_count;
	private TextView app_type;
	private TextView app_version;
	private TextView app_size;
	private TextView update_time;
	private TextView app_desc;
	private RatingView ratingView;
	
	private String product_model;
	private String product_serialnum;
	
	private SliderIndicatorBarView sliderIndicatorBarView;
	
	private ViewPager app_imgs;
	private AppImgsViewPagerAdapter appImgsViewPagerAdapter;
	
	private PopupWindow popupWindow;
	
	private List<String> appImgs = new ArrayList<String>();
	
	private AppBean selectedApp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		product_model = SystemProperties.get("ro.product.model", "");
		product_serialnum = SystemProperties.get("ro.product.stb.serialnum", "");
		
		appScoreUpdateService = new AppScoreUpdateService(getContext());
		
		setContentView(R.layout.activity_app_detail);
		
		Intent intent = getIntent();
		selectedApp = (AppBean) intent.getSerializableExtra("selectedApp");
		
		sliderIndicatorBarView = (SliderIndicatorBarView) findViewById(R.id.slider_bar);
		
		app_logo = (ImageView) findViewById(R.id.app_logo);
		app_name = (TextView) findViewById(R.id.app_name);
		download_count = (TextView) findViewById(R.id.download_count);
		app_type = (TextView) findViewById(R.id.app_type);
		app_version = (TextView) findViewById(R.id.app_version);
		app_size = (TextView) findViewById(R.id.app_size);
		update_time = (TextView) findViewById(R.id.update_time);
		app_desc = (TextView) findViewById(R.id.app_desc);
		ratingView = (RatingView) findViewById(R.id.ratingView);
		
		Glide.with(getContext()).load(Constants.IMAGE_URL + selectedApp.getAppLogo()).into(app_logo);
		app_name.setText(selectedApp.getAppName());
		download_count.setText("4605次下载");
		app_type.setText("应用类型：启蒙教育");
		app_version.setText("当前版本：" + selectedApp.getAppVersion() + "版");
		app_size.setText("应用大小：" + selectedApp.getAppSize() + "M");
		update_time.setText("更新时间：" + selectedApp.getUpdateTime());
		app_desc.setText(selectedApp.getAppIntro());
		ratingView.setScore(selectedApp.getScore());
		
		root_view = findViewById(R.id.root_view);
		
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_rate = (Button) findViewById(R.id.btn_rate);
		btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
		
		btn_start.setOnClickListener(this);
		btn_rate.setOnClickListener(this);
		btn_uninstall.setOnClickListener(this);
		
		app_imgs = (ViewPager) findViewById(R.id.app_imgs);
		if (!TextUtils.isEmpty(selectedApp.getAppImg1())) {
			appImgs.add(selectedApp.getAppImg1());
		}
		if (!TextUtils.isEmpty(selectedApp.getAppImg2())) {
			appImgs.add(selectedApp.getAppImg2());
		}
		if (!TextUtils.isEmpty(selectedApp.getAppImg3())) {
			appImgs.add(selectedApp.getAppImg3());
		}
		if (!TextUtils.isEmpty(selectedApp.getAppImg4())) {
			appImgs.add(selectedApp.getAppImg4());
		}
		if (!TextUtils.isEmpty(selectedApp.getAppImg5())) {
			appImgs.add(selectedApp.getAppImg5());
		}
//		appImgs.add("https://img3.doubanio.com/icon/ul63839584-4.jpg");
//		appImgs.add("https://img1.doubanio.com/icon/ul47183650-28.jpg");
//		appImgs.add("https://img3.doubanio.com/icon/ul79926360-73.jpg");
//		appImgs.add("https://www.baidu.com/img/bd_logo1.png");
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
				RatingBarView RatingBarView = (com.funo.appmarket.view.RatingBarView) rootView.findViewById(R.id.ratingBarView);
				RatingBarView.setRatingCallback(new RatingCallback() {
					
					@Override
					public void rate(int rating) {
						AppScoreUpdateParam appScoreUpdateParam = new AppScoreUpdateParam();
						appScoreUpdateParam.appId = selectedApp.getAppId();
						appScoreUpdateParam.eqId = "123";
						appScoreUpdateParam.score = rating;
						appScoreUpdateService.appScoreUpdate(appScoreUpdateParam, new AppScoreUpdateCallback() {
							
							@Override
							public void doCallback() {
								ToastUtils.showShortToast(getContext(), "感谢您的评分");
								if (popupWindow != null && popupWindow.isShowing()) {
									popupWindow.dismiss();
								}
							}
							
						});
					}
					
				});
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
