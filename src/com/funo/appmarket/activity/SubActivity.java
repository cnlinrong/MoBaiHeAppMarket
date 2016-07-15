package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.view.RecommendedAppsView;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SubActivity extends BaseActivity {

	private ListView navList;
	private View search;
	
	private RecommendedAppsView gl_gridlayout;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	MainUpView mainUpView1;
	RecyclerViewBridge mRecyclerViewBridge;
	private View oldView;
	
	private NavListAdapter navListAdapter;
	private List<NavItem> navItems = new ArrayList<NavItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sub);

		search = findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getContext(), SearchActivity.class));
			}
			
		});
		
		gl_gridlayout = (RecommendedAppsView) findViewById(R.id.gl_gridlayout);
		appBeans.add(new AppBean("飞上天空", "世界那么大 我想去看看"));
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));
		appBeans.add(new AppBean("移动音乐会", "宝宝音乐"));
		appBeans.add(new AppBean("乌鸦", "每天疯狂一下"));
		appBeans.add(new AppBean("儿童绘画", "每天疯狂一下"));
		appBeans.add(new AppBean("爱奇艺", "每天疯狂一下"));
		appBeans.add(new AppBean("萌宠", "每天疯狂一下"));
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));
		gl_gridlayout.setAppData(appBeans);
		
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		mainUpView1.setEffectBridge(new RecyclerViewBridge());
		mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
		mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
		GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(this, 3);
		gridlayoutManager.setLeftPadding((int) getResources().getDimension(R.dimen.px250));
		gridlayoutManager.setRightPadding((int) getResources().getDimension(R.dimen.px150));
		gridlayoutManager.setOnChildSelectedListener(new OnChildSelectedListener() {
			
			@Override
			public void onChildSelected(RecyclerView parent, View focusview, int position, int dy) {
				focusview.bringToFront();
				mRecyclerViewBridge.setFocusView(focusview, oldView, 1.2f);
				oldView = focusview;
			}
			
		});
		
		navList = (ListView) findViewById(R.id.navList);
		navList.post(new Runnable() {

			@Override
			public void run() {
				int itemHeight = navList.getHeight() / 4 + 1;
				NavItem navItem = new NavItem("游戏", "", "");
				navItems.add(navItem);
				navItem = new NavItem("教育阅读", "", "");
				navItems.add(navItem);
				navItem = new NavItem("生活助手", "", "");
				navItems.add(navItem);
				navItem = new NavItem("亲子乐园", "", "");
				navItems.add(navItem);
				navItem = new NavItem("亲子啊啊", "", "");
				navItems.add(navItem);
				navListAdapter = new NavListAdapter(getContext(), navItems, itemHeight);
				navList.setAdapter(navListAdapter);
			}
			
		});
		navList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(getContext(), AppsActivity.class));
			}
			
		});
	}

}
