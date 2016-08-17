package com.funo.appmarket.adapter;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.GetTopAppService;
import com.funo.appmarket.business.GetTopAppService.GetTopAppCallback;
import com.funo.appmarket.business.define.IGetTopAppService.GetTopAppParam;
import com.funo.appmarket.util.ToastUtils;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLayoutChangeListener;
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
	
	private SparseArray<GridView> gridViews = new SparseArray<GridView>();
	
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
			
			final MainUpView mainUpView1 = (MainUpView) rootView.findViewById(R.id.mainUpView1);
			EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
	        effectNoDrawBridge.setTranDurAnimTime(200);
	        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
	        mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
	        mainUpView1.setDrawUpRectPadding(2);
	        
			apps_list = (GridView) rootView.findViewById(R.id.apps_list);
			apps_list.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					/**
					 * 这里注意要加判断是否为NULL.
					 * 因为在重新加载数据以后会出问题.
					 */
					if (view != null && parent.isFocused() && mOldView != view) {
						view.bringToFront();
						mainUpView1.setFocusView(view, mOldView, 1.1f);
						mOldView = view;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					mainUpView1.setUnFocusView(mOldView);
					mainUpView1.setVisibility(View.GONE);
				}
				
			});
			apps_list.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						View selectedView = apps_list.getSelectedView();
						if (selectedView != null) {
							selectedView.bringToFront();
							mainUpView1.setFocusView(selectedView, 1.1f);
						}
					} else {
						mainUpView1.setUnFocusView(mOldView);
						mainUpView1.setVisibility(View.GONE);
					}
				}
				
			});
			
			// 在布局加咱完成后，设置选中第一个 (test)
			apps_list.addOnLayoutChangeListener(new OnLayoutChangeListener() {

				@Override
				public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
						int oldRight, int oldBottom) {
					if (apps_list.hasFocus() && apps_list.getChildCount() > 0 && mOldView == null) {
						apps_list.setSelection(0);
						View newView = apps_list.getChildAt(0);
						newView.bringToFront();
						mainUpView1.setFocusView(newView, 1.1f);
						mOldView = newView;
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
			
			gridViews.put(position, apps_list);
			
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
	
	public void resetOldView() {
		mOldView = null;
	}
	
	public void resetGridViews(int selectedPosition) {
		for (int i = 0; i < gridViews.size(); i++) {
			if (selectedPosition != i) {
				gridViews.get(i).setSelection(0);
			}
		}
	}
	
}
