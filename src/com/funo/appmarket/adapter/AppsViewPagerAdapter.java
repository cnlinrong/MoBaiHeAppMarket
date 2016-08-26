package com.funo.appmarket.adapter;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.SearchAppByTypeService;
import com.funo.appmarket.business.SearchAppByTypeService.SearchAppByTypeCallback;
import com.funo.appmarket.business.define.ISearchAppByTypeService.SearchAppByTypeParam;

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

public class AppsViewPagerAdapter extends FragmentPagerAdapter {

	private SearchAppByTypeService searchAppByTypeService;
	
	private Context mContext;
	private int pageCount;
	private int orderType;
	private String subParentId;
	
	private View mOldView;
	
	private int pageSize = 15;
	
	public AppsViewPagerAdapter(FragmentManager fm, Context context, int pageCount, int orderType, String subParentId) {
		super(fm);
		
		List<Fragment> fragments = fm.getFragments();
		if (fragments != null && !fragments.isEmpty()) {
			for (Fragment fragment : fragments) {
				fm.beginTransaction().remove(fragment).commit();
			}
		}
		
		this.mContext = context;
		this.pageCount = pageCount;
		this.orderType = orderType;
		this.subParentId = subParentId;
		
		searchAppByTypeService = new SearchAppByTypeService(mContext);
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
		
		GridView apps_list;
		
		AppsGridViewAdapter appsGridViewAdapter;
		
		private boolean focus_inited = false;// 让聚焦监听只处理一次焦点
		
		public MyFragment(int position) {
			this.position = position;
		}
		
		@SuppressLint("InflateParams")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_apps_view_pager, null);
			
			apps_list = (GridView) rootView.findViewById(R.id.apps_list);
			apps_list.setOnItemSelectedListener(new OnItemSelectedListener() {

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
			apps_list.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus && !focus_inited) {
						View selectedView = apps_list.getSelectedView();
						if (selectedView != null) {
							selectedView.bringToFront();
							selectedView.findViewById(R.id.overlay).setVisibility(View.VISIBLE);
							selectedView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(500).start();
							mOldView = selectedView;
							
							focus_inited = true;
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
			
			appsGridViewAdapter = new AppsGridViewAdapter(getContext());
			apps_list.setAdapter(appsGridViewAdapter);
			apps_list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(getContext(), AppDetailActivity.class);
					intent.putExtra("selectedApp", appsGridViewAdapter.getItem(position));
					startActivity(intent);
				}
				
			});
			
			return rootView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
			SearchAppByTypeParam searchAppByTypeParam = new SearchAppByTypeParam();
			searchAppByTypeParam.smallTypeId = subParentId;
			searchAppByTypeParam.orderType = orderType;
			searchAppByTypeParam.pageSize = pageSize;
			searchAppByTypeParam.currentPage = position + 1;
			searchAppByTypeService.searchAppByType(searchAppByTypeParam, new SearchAppByTypeCallback() {
				
				@Override
				public void doCallback(List<AppBean> appBeans, int pageCount) {
					if (appBeans != null) {
						AppsViewPagerAdapter.this.pageCount = pageCount;
						appsGridViewAdapter.setData(appBeans);
					}
				}
				
			});
		}
		
	}
	
}
