package com.funo.appmarket.adapter;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.GetTopAppService;
import com.funo.appmarket.business.GetTopAppService.GetTopAppCallback;
import com.funo.appmarket.business.define.IGetTopAppService.GetTopAppParam;

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

public class RankListViewPagerAdapter extends FragmentPagerAdapter {

	private GetTopAppService getTopAppService;
	
	private Context mContext;
	private int pageCount;
	private int orderType;
	
	private View mOldView;
	
	private int pageSize = 15;
	
	public RankListViewPagerAdapter(FragmentManager fm, Context context, int pageCount, int orderType) {
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
		
		getTopAppService = new GetTopAppService(mContext);
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
		
		RankListGridViewAdapter rankListGridViewAdapter;
		
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
					if (hasFocus) {
						View selectedView = apps_list.getSelectedView();
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
			
			rankListGridViewAdapter = new RankListGridViewAdapter(getContext());
			apps_list.setAdapter(rankListGridViewAdapter);
			apps_list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(getContext(), AppDetailActivity.class);
					intent.putExtra("selectedApp", rankListGridViewAdapter.getItem(position));
					startActivity(intent);
				}
				
			});
			
			return rootView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			
			GetTopAppParam getTopAppParam = new GetTopAppParam();
			getTopAppParam.orderType = orderType;
			getTopAppParam.pageSize = pageSize;
			getTopAppParam.currentPage = position + 1;
			getTopAppService.getTopApp(getTopAppParam,  new GetTopAppCallback() {
				
				@Override
				public void doCallback(List<AppBean> appBeans, int pageCount) {
					if (appBeans != null) {
						RankListViewPagerAdapter.this.pageCount = pageCount;
						rankListGridViewAdapter.setData(appBeans);
					}
				}
				
			});
		}
		
	}
	
}
