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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;

public class InstalledViewPagerAdapter extends FragmentPagerAdapter {

	private View mOldView = null;
	
	private int pageSize = 15;
	
	private InstalledAppInfoService installedAppInfoService;
	
	public InstalledViewPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		installedAppInfoService = new InstalledAppInfoService(context);
	}

	@Override
	public Fragment getItem(int position) {
		return new MyFragment(position);
	}

	@Override
	public int getCount() {
		return AppModelDB.getInstalledAppsPageCount(pageSize);
	}

	class MyFragment extends Fragment {

		int position;
		
		GridView installed_list;
		
		InstalledAppsGridViewAdapter installedAppsGridViewAdapter;
		
		public MyFragment(int position) {
			this.position = position;
		}
		
		@SuppressLint("InflateParams")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_apps_view_pager, null);
			
			installed_list = (GridView) rootView.findViewById(R.id.apps_list);
			installed_list.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					if (view != null && parent.isFocused() && mOldView != view) {
						if (mOldView != null) {
							mOldView.findViewById(R.id.overlay).setVisibility(View.GONE);
							mOldView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
						}
						view.bringToFront();
						view.findViewById(R.id.overlay).setVisibility(View.VISIBLE);
						view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(500).start();
						mOldView = view;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
				
			});
			installed_list.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						View selectedView = installed_list.getSelectedView();
						if (selectedView != null) {
							selectedView.bringToFront();
							selectedView.findViewById(R.id.overlay).setVisibility(View.VISIBLE);
							selectedView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(500).start();
							mOldView = selectedView;
						}
					} else {
						if (mOldView != null) {
							mOldView.findViewById(R.id.overlay).setVisibility(View.GONE);
							mOldView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
							mOldView = null;
						}
					}
				}
				
			});
			
			installedAppsGridViewAdapter = new InstalledAppsGridViewAdapter(getContext());
			installed_list.setAdapter(installedAppsGridViewAdapter);
			installed_list.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(getContext(), AppDetailActivity.class);
					intent.putExtra("selectedApp", installedAppsGridViewAdapter.getItem(position));
					startActivity(intent);
				}
				
			});
			
			return rootView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
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
							installedAppsGridViewAdapter.setData(appBeans);
						}
					}
					
				});
			}
		}
		
	}
	
}
