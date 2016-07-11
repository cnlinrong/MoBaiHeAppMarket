package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ToastUtils;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SubActivity extends BaseActivity {

	private ListView navList;
	private View search;
	
	private GridLayout gl_gridlayout;
	
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
				ToastUtils.showShortToast(getContext(), "搜索");
			}
			
		});
		
		gl_gridlayout = (GridLayout) findViewById(R.id.gl_gridlayout);
		gl_gridlayout.setColumnCount(7);
		gl_gridlayout.setRowCount(2);
		gl_gridlayout.setOrientation(GridLayout.HORIZONTAL);

		gl_gridlayout.post(new Runnable() {

			@Override
			public void run() {
				int height = gl_gridlayout.getHeight();
				int itemWidth = height / 3 + 77;

				View v1 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v1.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v1.findViewById(R.id.content_tv)).setText("游戏1");
				v1.setMinimumWidth(itemWidth * 2);
				GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 2);
				GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
				params.setGravity(Gravity.FILL);
				gl_gridlayout.addView(v1, params);

				View v2 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v2.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v2.findViewById(R.id.content_tv)).setText("游戏2");
				v2.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec2 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec2 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params2 = new GridLayout.LayoutParams(rowSpec2, columnSpec2);
				gl_gridlayout.addView(v2, params2);

				View v3 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v3.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v3.findViewById(R.id.content_tv)).setText("游戏3");
				v3.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec3 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec3 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params3 = new GridLayout.LayoutParams(rowSpec3, columnSpec3);
				gl_gridlayout.addView(v3, params3);

				View v4 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v4.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v4.findViewById(R.id.content_tv)).setText("游戏4");
				v4.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec4 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec4 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params4 = new GridLayout.LayoutParams(rowSpec4, columnSpec4);
				gl_gridlayout.addView(v4, params4);

				View v5 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v5.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v5.findViewById(R.id.content_tv)).setText("游戏5");
				v5.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec5 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec5 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params5 = new GridLayout.LayoutParams(rowSpec5, columnSpec5);
				gl_gridlayout.addView(v5, params5);
				
				View v6 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v6.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v6.findViewById(R.id.content_tv)).setText("游戏6");
				v6.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec6 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec6 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params6 = new GridLayout.LayoutParams(rowSpec6, columnSpec6);
				gl_gridlayout.addView(v6, params6);

				View v7 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v7.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v7.findViewById(R.id.content_tv)).setText("游戏7");
				v7.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec7 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec7 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params7 = new GridLayout.LayoutParams(rowSpec7, columnSpec7);
				gl_gridlayout.addView(v7, params7);
				
				View v8 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v8.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v8.findViewById(R.id.content_tv)).setText("游戏8");
				v8.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec8 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec8 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params8 = new GridLayout.LayoutParams(rowSpec8, columnSpec8);
				gl_gridlayout.addView(v8, params8);

				View v9 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v9.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v9.findViewById(R.id.content_tv)).setText("游戏9");
				v9.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec9 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec9 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params9 = new GridLayout.LayoutParams(rowSpec9, columnSpec9);
				gl_gridlayout.addView(v9, params9);
				
				View v10 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v10.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v10.findViewById(R.id.content_tv)).setText("游戏10");
				v10.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec10 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec10 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params10 = new GridLayout.LayoutParams(rowSpec10, columnSpec10);
				gl_gridlayout.addView(v10, params10);

				View v11 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v11.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v11.findViewById(R.id.content_tv)).setText("游戏11");
				v11.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec11 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec11 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params11 = new GridLayout.LayoutParams(rowSpec11, columnSpec11);
				gl_gridlayout.addView(v11, params11);
				
				View v12 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v12.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v12.findViewById(R.id.content_tv)).setText("游戏12");
				v12.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec12 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec12 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params12 = new GridLayout.LayoutParams(rowSpec12, columnSpec12);
				gl_gridlayout.addView(v12, params12);

				View v13 = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v13.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v13.findViewById(R.id.content_tv)).setText("游戏13");
				v13.setMinimumWidth(itemWidth);
				GridLayout.Spec rowSpec13 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec13 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params13 = new GridLayout.LayoutParams(rowSpec13, columnSpec13);
				gl_gridlayout.addView(v13, params13);
			}

		});
		
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
				int itemHeight = navList.getHeight() / 4;
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
