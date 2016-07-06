package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.adapter.RecyclerViewAdapter;
import com.funo.appmarket.bean.NavItem;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

public class MainActivity2 extends BaseActivity {

	private ListView navList;
	
	RecyclerViewTV recyclerView;
	MainUpView mainUpView1;
	RecyclerViewBridge mRecyclerViewBridge;
	private View oldView;
	
	private NavListAdapter navListAdapter;
	private List<NavItem> navItems = new ArrayList<NavItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main2);

		recyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
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
		gridlayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
		recyclerView.setLayoutManager(gridlayoutManager);
		recyclerView.setFocusable(false);
		final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(20);
		recyclerView.setAdapter(recyclerViewAdapter);
		
		navList = (ListView) findViewById(R.id.navList);
		NavItem navItem = new NavItem("游戏", "", "");
		navItems.add(navItem);
		navItem = new NavItem("教育阅读", "", "");
		navItems.add(navItem);
		navItem = new NavItem("生活助手", "", "");
		navItems.add(navItem);
		navItem = new NavItem("亲子乐园", "", "");
		navItems.add(navItem);
		navListAdapter = new NavListAdapter(getContext(), navItems);
		navList.setAdapter(navListAdapter);
	}

}
