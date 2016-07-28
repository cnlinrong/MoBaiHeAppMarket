package com.funo.appmarket.adapter;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.SearchAppByTypeService;
import com.funo.appmarket.business.SearchAppByTypeService.SearchAppByTypeCallback;
import com.funo.appmarket.business.define.ISearchAppByTypeService.SearchAppByTypeParam;
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
	
	public AppsViewPagerAdapter(FragmentManager fm, Context context, int pageCount, int orderType, String subParentId) {
		super(fm);
		
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
	        
			final GridView apps_list = (GridView) rootView.findViewById(R.id.apps_list);
			apps_list.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					/**
					 * 这里注意要加判断是否为NULL.
					 * 因为在重新加载数据以后会出问题.
					 */
					if (view != null) {
						view.bringToFront();
						mainUpView1.setFocusView(view, mOldView, 1.1f);
					}
					mOldView = view;
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
				
			});
			
			SearchAppByTypeParam searchAppByTypeParam = new SearchAppByTypeParam();
			searchAppByTypeParam.smallTypeId = subParentId;
			searchAppByTypeParam.orderType = orderType;
			searchAppByTypeParam.pageSize = 15;
			searchAppByTypeParam.currentPage = 1;
			searchAppByTypeService.searchAppByType(searchAppByTypeParam, new SearchAppByTypeCallback() {
				
				@Override
				public void doCallback(List<AppBean> appBeans) {
					if (appBeans != null) {
						final AppsGridViewAdapter appsGridViewAdapter = new AppsGridViewAdapter(getContext(), appBeans);
						apps_list.setAdapter(appsGridViewAdapter);
						apps_list.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								Intent intent = new Intent(getContext(), AppDetailActivity.class);
								intent.putExtra("selectedApp", appsGridViewAdapter.getItem(position));
								startActivity(intent);
							}
							
						});
					}
				}
				
			});
			return rootView;
		}
		
	}
	
}
