package com.funo.appmarket.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.funo.appmarket.db.AppModelDB;
import com.funo.appmarket.util.InstallUtils;
import com.funo.appmarket.util.ModelBeanConverter;
import com.funo.appmarket.util.PackageUtils;
import com.funo.appmarket.util.ToastUtils;
import com.funo.appmarket.view.RatingBarView;
import com.funo.appmarket.view.RatingBarView.RatingCallback;
import com.funo.appmarket.view.RatingView;
import com.funo.appmarket.view.SliderIndicatorBarView;
import com.hellobird.circleseekbar.CircleSeekBar;
import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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

	private int slider_interval_time = 3000;
	
	private AppScoreUpdateService appScoreUpdateService;
	
	private View root_view;
	private Button btn_download;
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
	
	private CircleSeekBar circleSeekBar;
	
	private String product_model;
	private String product_serialnum;
	
	private SliderIndicatorBarView sliderIndicatorBarView;
	
	private ViewPager app_imgs;
	private AppImgsViewPagerAdapter appImgsViewPagerAdapter;
	
	private PopupWindow popupWindow;
	
	private List<String> appImgs = new ArrayList<String>();
	
	private AppBean selectedApp;
	
	private boolean installed_flag = false;// 是否已安装
	private String packageName = "com.cemobile.schoolble";// 包名
	
	private String mSavePath;
	private String apkPath;
	private float mProgress;

	private static final int DOWNLOADING = 1;
	private static final int DOWNLOAD_FINISH = 2;
	private static final int DOWNLOAD_CANCEL = 3;
	private static final int DOWNLOAD_FAIL = 4;

	private String apk_name = "test.apk";

	private String downloadUrl = Constants.TEST_DOWNLOAD_URL;
	
	@SuppressWarnings("deprecation")
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
		
		circleSeekBar = (CircleSeekBar) findViewById(R.id.circleSeekBar);
		
		Glide.with(getContext()).load(Constants.IMAGE_URL + selectedApp.getAppLogo()).into(app_logo);
		app_name.setText(selectedApp.getAppName());
		download_count.setText(selectedApp.getDownnum() + "次下载");
		app_type.setText("应用类型：" + selectedApp.getAppSubType());
		app_version.setText("当前版本：" + selectedApp.getAppVersion() + "版");
		app_size.setText("应用大小：" + selectedApp.getAppSize() + "M");
		update_time.setText("更新时间：" + selectedApp.getUpdateTime());
		app_desc.setText(selectedApp.getAppIntro());
		ratingView.setScore(selectedApp.getScore());
		
		root_view = findViewById(R.id.root_view);
		
		btn_download = (Button) findViewById(R.id.btn_download);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_rate = (Button) findViewById(R.id.btn_rate);
		btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
		
		btn_download.setOnClickListener(this);
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
		appImgs.add("https://img3.doubanio.com/icon/ul63839584-4.jpg");
		appImgs.add("https://img1.doubanio.com/icon/ul47183650-28.jpg");
		appImgs.add("https://img3.doubanio.com/icon/ul79926360-73.jpg");
		appImgs.add("https://www.baidu.com/img/bd_logo1.png");
		appImgsViewPagerAdapter = new AppImgsViewPagerAdapter(getSupportFragmentManager(), getContext(), appImgs);
		app_imgs.setAdapter(appImgsViewPagerAdapter);
		int middlePosition = appImgsViewPagerAdapter.getCount() / 2 / appImgs.size() * appImgs.size();
		app_imgs.setCurrentItem(middlePosition);
		app_imgs.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				sliderIndicatorBarView.selectIndicator(position % appImgs.size());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
			
		});
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				app_imgs.setCurrentItem(app_imgs.getCurrentItem() + 1);
				app_imgs.postDelayed(this, slider_interval_time);
			}
			
		};
		
		app_imgs.postDelayed(r, slider_interval_time);
		
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
	protected void onResume() {
		super.onResume();
		
		installed_flag = InstallUtils.hasInstalled(getContext(), packageName);
		if (installed_flag) {
			btn_start.setVisibility(View.VISIBLE);
			btn_uninstall.setVisibility(View.VISIBLE);
			btn_download.setVisibility(View.GONE);
		} else {
			btn_start.setVisibility(View.GONE);
			btn_uninstall.setVisibility(View.GONE);
			btn_download.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_download:
			ToastUtils.showShortToast(getContext(), "开始下载");
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
							String sdPath = Environment.getExternalStorageDirectory() + "/";
							mSavePath = sdPath + "mobaihe-cache";

							File dir = new File(mSavePath);
							if (!dir.exists())
								dir.mkdir();

							// 下载文件
							HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
							conn.setRequestMethod("POST");
							InputStream is = conn.getInputStream();
							int length = conn.getContentLength();

							apkPath = mSavePath + "/" + apk_name;
							File apkFile = new File(apkPath);
							FileOutputStream fos = new FileOutputStream(apkFile);

							int count = 0;
							byte[] buffer = new byte[1024];
							while (true) {
								int numread = is.read(buffer);
								count += numread;
								// 计算进度条的当前位置
								mProgress = ((float) count / length) * 100;
								// 更新进度条
								mUpdateProgressHandler.sendEmptyMessage(DOWNLOADING);

								// 下载完成
								if (numread < 0) {
									mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
									break;
								}
								fos.write(buffer, 0, numread);
							}
							fos.close();
							is.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
						
						CrashReport.postCatchedException(new RuntimeException("下载文件失败", e));
						
						mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FAIL);
					}
				}

			}).start();
			break;
		case R.id.btn_start:
			PackageManager packageManager = getPackageManager();
			Intent intent = packageManager.getLaunchIntentForPackage(packageName);
			startActivity(intent);
			break;
		case R.id.btn_rate:
			if (popupWindow != null && !popupWindow.isShowing()) {
				popupWindow.showAtLocation(root_view, Gravity.BOTTOM, 0, 0);
			}
			break;
		case R.id.btn_uninstall:
			int result = PackageUtils.uninstall(getContext(), packageName);
			break;
		}
	}

	private Handler mUpdateProgressHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOADING:
				// 设置进度条
				circleSeekBar.setProgress(mProgress);
				break;
			case DOWNLOAD_FINISH:
				circleSeekBar.setVisibility(View.GONE);
				
				int result = PackageUtils.install(getContext(), apkPath);
				if (result == PackageUtils.INSTALL_SUCCEEDED) {
					selectedApp.setPkgname(packageName);
					AppModelDB.saveApp(ModelBeanConverter.appBean2Model(selectedApp));
				}
				break;
			case DOWNLOAD_CANCEL:
				break;
			case DOWNLOAD_FAIL:
				ToastUtils.showShortToast(getContext(), "下载文件失败");
				break;
			}
		}

	};
	
	@Override
	public void onBackPressed() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			return;
		}
		super.onBackPressed();
	}
	
}
