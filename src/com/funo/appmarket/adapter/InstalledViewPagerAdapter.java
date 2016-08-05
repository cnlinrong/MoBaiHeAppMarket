package com.funo.appmarket.adapter;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.InstalledAppInfoService;
import com.funo.appmarket.business.InstalledAppInfoService.InstalledAppInfoCallback;
import com.funo.appmarket.business.define.IInstalledAppInfoService.InstalledAppInfoParam;
import com.funo.appmarket.db.AppModelDB;
import com.funo.appmarket.model.AppModel;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;

public class InstalledViewPagerAdapter extends FragmentPagerAdapter {

	private int pageCount;
	
	private View mOldView;
	
	private int pageSize = 15;
	
	private InstalledAppInfoService installedAppInfoService;
	
	public InstalledViewPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		this.pageCount = AppModelDB.getInstalledAppsPageCount(pageSize);
		
		installedAppInfoService = new InstalledAppInfoService(context);
	}

	@Override
	public Fragment getItem(int position) {
		return new MyFragment(position);
	}

	@Override
	public int getCount() {
		return pageCount;
	}

	class MyFragment extends Fragment {

		int position;
		
		public MyFragment() {
			super();
		}
		
		public MyFragment(int position) {
			this.position = position;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_apps_view_pager, null);
			
			final MainUpView mainUpView1 = (MainUpView) rootView.findViewById(R.id.mainUpView1);
			EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
	        effectNoDrawBridge.setTranDurAnimTime(200);
	        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
	        mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
	        mainUpView1.setDrawUpRectPadding(2);
	        
			final GridView installed_list = (GridView) rootView.findViewById(R.id.apps_list);
			installed_list.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					/**
					 * 这里注意要加判断是否为NULL.
					 * 因为在重新加载数据以后会出问题.
					 */
					if (view != null && parent.isFocused()) {
						view.bringToFront();
						mainUpView1.setFocusView(view, mOldView, 1.1f);
					}
					mOldView = view;
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					mainUpView1.setUnFocusView(mOldView);
					mainUpView1.setVisibility(View.GONE);
				}
				
			});
			installed_list.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						mainUpView1.setUnFocusView(mOldView);
						mainUpView1.setVisibility(View.GONE);
					}
				}
				
			});
			
			List<AppModel> appModels = AppModelDB.getAllInstalledApps(position + 1, pageSize);
			if (appModels != null && !appModels.isEmpty()) {
				StringBuilder appIds = new StringBuilder();
				for (int i = 0; i < appModels.size(); i++) {
					if (i != appModels.size() - 1) {
						appIds.append(appModels.get(i).getAppId() + ",");
					} else {
						appIds.append(appModels.get(i).getAppId() + "");
					}
				}
				InstalledAppInfoParam installedAppInfoParam = new InstalledAppInfoParam();
				installedAppInfoParam.appId = appIds.toString();
				installedAppInfoParam.pageSize = Integer.MAX_VALUE;
				installedAppInfoParam.currentPage = 1;
				installedAppInfoService.installedAppInfo(installedAppInfoParam, new InstalledAppInfoCallback() {
					
					@Override
					public void doCallback(List<AppBean> appBeans) {
						if (appBeans != null) {
							final InstalledAppsGridViewAdapter installedAppsGridViewAdapter = new InstalledAppsGridViewAdapter(getContext(), appBeans);
							installed_list.setAdapter(installedAppsGridViewAdapter);
							installed_list.setOnItemClickListener(new OnItemClickListener() {
								
								@Override
								public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
									Intent intent = new Intent(getContext(), AppDetailActivity.class);
									intent.putExtra("selectedApp", installedAppsGridViewAdapter.getItem(position));
									startActivity(intent);
								}
								
							});
						}
					}
					
				});
			}
			
			// 在布局加咱完成后，设置选中第一个 (test)
			installed_list.addOnLayoutChangeListener(new OnLayoutChangeListener() {

				@Override
				public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
						int oldRight, int oldBottom) {
					if (installed_list.hasFocus() && installed_list.getChildCount() > 0) {
						installed_list.setSelection(0);
						View newView = installed_list.getChildAt(0);
						newView.bringToFront();
						mainUpView1.setFocusView(newView, 1.1f);
						mOldView = installed_list.getChildAt(0);
					}
				}

			});
			
			return rootView;
		}
		
	}
	
}
