package com.funo.appmarket.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.AppImgsViewPagerAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.AppScoreUpdateService;
import com.funo.appmarket.business.AppScoreUpdateService.AppScoreUpdateCallback;
import com.funo.appmarket.business.SyncAppDownService;
import com.funo.appmarket.business.SyncAppDownService.SyncAppDownCallback;
import com.funo.appmarket.business.define.IAppScoreUpdateService.AppScoreUpdateParam;
import com.funo.appmarket.business.define.ISyncAppDownService.SyncAppDownParam;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.db.AppModelDB;
import com.funo.appmarket.model.AppModel;
import com.funo.appmarket.util.InstallUtils;
import com.funo.appmarket.util.ModelBeanConverter;
import com.funo.appmarket.util.PackageUtils;
import com.funo.appmarket.util.ShellUtils;
import com.funo.appmarket.util.ToastUtils;
import com.funo.appmarket.view.RatingBarView;
import com.funo.appmarket.view.RatingBarView.RatingCallback;
import com.funo.appmarket.view.RatingView;
import com.funo.appmarket.view.SliderIndicatorBarView;
import com.hellobird.circleseekbar.CircleSeekBar;
import com.tencent.bugly.crashreport.CrashReport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

public class AppDetailActivity extends BaseActivity implements OnClickListener {

	private int slider_interval_time = 3000;
	
	private SyncAppDownService syncAppDownService;
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
	private RatingBarView ratingBarView;
	
	private List<String> appImgs = new ArrayList<String>();
	
	private AppBean selectedApp;
	
	private boolean installed_flag = false;// 是否已安装
	private String packageName;// 包名
	
	private String mSavePath;
	private String apkPath;
	private float mProgress;

	private static final int DOWNLOADING = 1;
	private static final int DOWNLOAD_FINISH = 2;
	private static final int DOWNLOAD_CANCEL = 3;
	private static final int DOWNLOAD_FAIL = 4;

	private String apk_name;

	private String downloadUrl;
	
	private boolean install_finished = true;
	
	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_app_detail);
		
		initViews();
		
		String sdPath = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			sdPath = Environment.getExternalStorageDirectory() + "/";
		} else {
			sdPath = Environment.getDownloadCacheDirectory() + "/";
		}
		mSavePath = sdPath + "mobaihe-cache";
		
		File dir = new File(mSavePath);
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					mSavePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
				} else {
					mSavePath = Environment.getDownloadCacheDirectory().getAbsolutePath();
				}
			}
		}
		
		product_model = SystemProperties.get("ro.product.model", "");
		product_serialnum = SystemProperties.get("ro.product.stb.serialnum", "");
		
		syncAppDownService = new SyncAppDownService(getContext());
		appScoreUpdateService = new AppScoreUpdateService(getContext());
		
		Intent intent = getIntent();
		selectedApp = (AppBean) intent.getSerializableExtra("selectedApp");
		packageName = selectedApp.getPkgname();
//		packageName = "com.cemobile.schoolble";
		downloadUrl = Constants.IMAGE_URL + selectedApp.getUrl();
//		downloadUrl = Constants.TEST_DOWNLOAD_URL;
		apk_name = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1);
		apkPath = mSavePath + "/" + apk_name;
		if (new File(apkPath).exists()) {
			btn_download.setText("安装");
		}
		
		Glide.with(getContext()).load(Constants.IMAGE_URL + selectedApp.getAppLogo()).into(app_logo);
		app_name.setText(selectedApp.getAppName());
		download_count.setText(selectedApp.getDownnum() + "下载");
		app_type.setText("应用类型：" + selectedApp.getAppSubType());
		app_version.setText("当前版本：" + selectedApp.getAppVersion() + "版");
		app_size.setText("应用大小：" + selectedApp.getAppSize() + "M");
		if (!TextUtils.isEmpty(selectedApp.getUpdateTime())) {
			try {
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				Date updateTime = simpleDateFormat1.parse(selectedApp.getUpdateTime());
				update_time.setText("更新时间：" + simpleDateFormat2.format(updateTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		app_desc.setText(selectedApp.getAppIntro());
		ratingView.setScore(selectedApp.getScore());
		
		btn_download.setOnClickListener(this);
		btn_start.setOnClickListener(this);
		btn_rate.setOnClickListener(this);
		btn_uninstall.setOnClickListener(this);
		
		app_imgs = (ViewPager) findViewById(R.id.app_imgs);
		app_imgs.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					app_imgs.setBackgroundResource(R.drawable.test_rectangle);
				} else {
					app_imgs.setBackgroundColor(Color.parseColor("#55000000"));
				}
			}
			
		});
		
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
		appImgsViewPagerAdapter = new AppImgsViewPagerAdapter(getSupportFragmentManager(), getContext(), appImgs);
		app_imgs.setAdapter(appImgsViewPagerAdapter);
		if (!appImgs.isEmpty()) {
			int middlePosition = appImgsViewPagerAdapter.getCount() / 2 / appImgs.size() * appImgs.size();
			app_imgs.setCurrentItem(middlePosition);
		}
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
				ratingBarView = (com.funo.appmarket.view.RatingBarView) rootView.findViewById(R.id.ratingBarView);
				ratingBarView.setRatingCallback(new RatingCallback() {
					
					@Override
					public void rate(final int rating) {
						AppScoreUpdateParam appScoreUpdateParam = new AppScoreUpdateParam();
						appScoreUpdateParam.appId = selectedApp.getAppId();
						String brand = android.os.Build.BRAND;
						appScoreUpdateParam.eqId = brand + product_model + product_serialnum;
						appScoreUpdateParam.score = rating;
						appScoreUpdateService.appScoreUpdate(appScoreUpdateParam, new AppScoreUpdateCallback() {
							
							@Override
							public void doCallback(List<AppBean> appBeans) {
								if (appBeans != null && !appBeans.isEmpty()) {
									if (popupWindow != null && popupWindow.isShowing()) {
										popupWindow.dismiss();
										
										ratingBarView.reset();
										
										rateToast(rating);
										
										AppBean appBean = appBeans.get(0);
										ratingView.setScore(appBean.getScore());
										
										btn_rate.setText("已评价");
										btn_rate.setClickable(false);
										btn_rate.setFocusable(false);
										btn_rate.setFocusableInTouchMode(false);
										btn_rate.setBackgroundResource(android.R.color.darker_gray);
									}
								} else {
									if (popupWindow != null && popupWindow.isShowing()) {
										popupWindow.dismiss();
										
										ratingBarView.reset();
										
										ToastUtils.showShortToast(getContext(), "该应用已评价过");
										
										btn_rate.setText("已评价");
										btn_rate.setClickable(false);
										btn_rate.setFocusable(false);
										btn_rate.setFocusableInTouchMode(false);
										btn_rate.setBackgroundResource(android.R.color.darker_gray);
									}
								}
							}
							
						});
					}
					
				});
				popupWindow = new PopupWindow(rootView, LayoutParams.MATCH_PARENT, root_view.getHeight() / 2, true);
				popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.setOutsideTouchable(true);
				popupWindow.setOnDismissListener(new OnDismissListener() {
					
					@Override
					public void onDismiss() {
						if (ratingBarView != null) {
							ratingBarView.reset();
						}
					}
					
				});
			}
			
		});
	}

	private void initViews() {
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
		
		root_view = findViewById(R.id.root_view);
		
		btn_download = (Button) findViewById(R.id.btn_download);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_rate = (Button) findViewById(R.id.btn_rate);
		btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		installed_flag = InstallUtils.hasInstalled(getContext(), packageName);
		if (installed_flag) {
			btn_start.setVisibility(View.VISIBLE);
			btn_uninstall.setVisibility(View.VISIBLE);
			btn_download.setVisibility(View.GONE);
			
			btn_start.requestFocus();
		} else {
			btn_start.setVisibility(View.GONE);
			btn_uninstall.setVisibility(View.GONE);
			btn_download.setVisibility(View.VISIBLE);
			
			btn_download.requestFocus();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_download:
			if (new File(apkPath).exists()) {
				
				btn_download.setText("安装中");
				btn_download.setClickable(false);
				btn_download.setBackgroundResource(android.R.color.darker_gray);

				if (PackageUtils.isSystemApplication(getContext()) || ShellUtils.checkRootPermission()) {
					install_finished = false;
					installSilent();
				} else {
					AppModelDB.saveApp(ModelBeanConverter.appBean2Model(selectedApp));
					installNormal();
				}
			} else {
				btn_download.setText("下载中");
				btn_download.setClickable(false);
				btn_download.setBackgroundResource(android.R.color.darker_gray);
				
				ToastUtils.showShortToast(getContext(), "开始下载");
				
				SyncAppDownParam syncAppDownParam = new SyncAppDownParam();
				String brand = android.os.Build.BRAND;
				syncAppDownParam.eqNo = brand + product_model + product_serialnum;
				syncAppDownParam.appId = selectedApp.getAppId();
				syncAppDownService.syncAppDown(syncAppDownParam, new SyncAppDownCallback() {
					
					@Override
					public void doCallback(List<AppBean> appBeans) {
						if (appBeans != null && !appBeans.isEmpty()) {
							AppBean appBean = appBeans.get(0);
							download_count.setText(appBean.getDownnum() + "下载");
						}
					}
					
				});
				
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
							conn.setRequestMethod("POST");
							InputStream is = conn.getInputStream();
							int length = conn.getContentLength();
							
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
						} catch (Exception e) {
							e.printStackTrace();
							
							CrashReport.postCatchedException(new RuntimeException("下载文件失败", e));
							
							mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FAIL);
						}
					}

				}).start();
			}
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
			if (PackageUtils.isSystemApplication(getContext()) || ShellUtils.checkRootPermission()) {
				ToastUtils.showShortToast(getContext(), "开始卸载");
				
				btn_uninstall.setText("卸载中");
				btn_uninstall.setClickable(false);
				btn_uninstall.setBackgroundResource(android.R.color.darker_gray);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						final int result = PackageUtils.uninstallSilent(getContext(), packageName);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								if (result == PackageUtils.DELETE_SUCCEEDED) {
									ToastUtils.showShortToast(getContext(), "卸载成功");
									
									btn_uninstall.setText("卸载");
									btn_uninstall.setClickable(true);
									btn_uninstall.setBackgroundResource(R.drawable.selector_app_detail_btn);
									
									btn_start.setVisibility(View.GONE);
									btn_uninstall.setVisibility(View.GONE);
									btn_download.setVisibility(View.VISIBLE);
								} else {
									ToastUtils.showShortToast(getContext(), "卸载失败");
									
									btn_uninstall.setText("卸载");
									btn_uninstall.setClickable(true);
									btn_uninstall.setBackgroundResource(R.drawable.selector_app_detail_btn);
								}
							}
							
						});
					}
					
				}).start();
	        } else {
	        	if (!PackageUtils.uninstallNormal(getContext(), packageName)) {
	        		ToastUtils.showShortToast(getContext(), "卸载失败");
	        	}
	        }
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
				
				btn_download.setText("安装中");
				btn_download.setClickable(false);
				btn_download.setBackgroundResource(android.R.color.darker_gray);
				
				if (PackageUtils.isSystemApplication(getContext()) || ShellUtils.checkRootPermission()) {
					install_finished = false;
					installSilent();
				} else {
					AppModelDB.saveApp(ModelBeanConverter.appBean2Model(selectedApp));
					installNormal();
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
	
	private void installNormal() {
		if (!PackageUtils.installNormal(getContext(), apkPath)) {
			ToastUtils.showShortToast(getContext(), "安装失败");
			if (new File(apkPath).exists()) {
				btn_download.setText("安装");
				btn_download.setClickable(true);
				btn_download.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						installNormal();
					}
					
				});
			} else {
				btn_download.setText("下载");
				btn_download.setClickable(true);
				btn_download.setOnClickListener(AppDetailActivity.this);
			}
			btn_download.setBackgroundResource(R.drawable.selector_app_detail_btn);
		} else {
			if (new File(apkPath).exists()) {
				btn_download.setText("安装");
				btn_download.setClickable(true);
				btn_download.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						installNormal();
					}
					
				});
			} else {
				btn_download.setText("下载");
				btn_download.setClickable(true);
				btn_download.setOnClickListener(AppDetailActivity.this);
			}
			btn_download.setBackgroundResource(R.drawable.selector_app_detail_btn);
		}
	}
	
	private void installSilent() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				final int result = PackageUtils.installSilent(getContext(), apkPath);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						install_finished = true;
						
						if (result == PackageUtils.INSTALL_SUCCEEDED) {
							ToastUtils.showShortToast(getContext(), "安装成功");
							
							AppModel appModel = ModelBeanConverter.appBean2Model(selectedApp);
							appModel.setInstalled_flag(true);
							AppModelDB.saveApp(appModel);
							
							if (new File(apkPath).exists()) {
								btn_download.setText("安装");
							} else {
								btn_download.setText("下载");
							}
							btn_download.setClickable(true);
							btn_download.setOnClickListener(AppDetailActivity.this);
							btn_download.setBackgroundResource(R.drawable.selector_app_detail_btn);

							btn_start.setVisibility(View.VISIBLE);
							btn_uninstall.setVisibility(View.VISIBLE);
							btn_download.setVisibility(View.GONE);
						} else {
							AppModelDB.saveApp(ModelBeanConverter.appBean2Model(selectedApp));
							installNormal();
							
//							ToastUtils.showShortToast(getContext(), "安装失败");
//							if (new File(apkPath).exists()) {
//								btn_download.setText("安装");
//								btn_download.setClickable(true);
//								btn_download.setOnClickListener(new OnClickListener() {
//									
//									@Override
//									public void onClick(View v) {
//										installSilent();
//									}
//									
//								});
//							} else {
//								btn_download.setText("下载");
//								btn_download.setClickable(true);
//								btn_download.setOnClickListener(AppDetailActivity.this);
//							}
//							btn_download.setBackgroundResource(R.drawable.selector_app_detail_btn);
						}
					}

				});
			}

		}).start();
	}
	
	public void rateToast(float score) {
		Toast toast = new Toast(getContext());
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		View view = LayoutInflater.from(getContext()).inflate(R.layout.common_info_tips, null);
		RatingView ratingView = (RatingView) view.findViewById(R.id.ratingView);
		ratingView.setScore(score);
		toast.setView(view);
		toast.show();
	}
	
	@Override
	public void onBackPressed() {
		if (install_finished) {
			finish();
		} else {
			ToastUtils.showShortToast(getContext(), "正在安装中，请稍后...");
		}
	}
	
}
