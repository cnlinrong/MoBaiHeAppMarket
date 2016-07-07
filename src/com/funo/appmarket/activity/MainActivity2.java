package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.bean.NavItem;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.recycle.OnChildSelectedListener;
import com.open.androidtvwidget.view.MainUpView;

import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity2 extends BaseActivity {

	private ListView navList;
	
	private GridLayout gl_gridlayout;
	
	MainUpView mainUpView1;
	RecyclerViewBridge mRecyclerViewBridge;
	private View oldView;
	
	private NavListAdapter navListAdapter;
	private List<NavItem> navItems = new ArrayList<NavItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main2);

		gl_gridlayout = (GridLayout) findViewById(R.id.gl_gridlayout);
		gl_gridlayout.setColumnCount(7);
		gl_gridlayout.setRowCount(2);
		gl_gridlayout.setOrientation(GridLayout.HORIZONTAL);

		gl_gridlayout.post(new Runnable() {

			@Override
			public void run() {
				int height = gl_gridlayout.getHeight();
				int itemWidth = height / 3 + 77;

				Button tv = new Button(getContext());
				tv.setText("游戏1");
				tv.setWidth(itemWidth * 2);
				GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 2);
				GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
				params.setGravity(Gravity.FILL);
				gl_gridlayout.addView(tv, params);

				Button tv2 = new Button(getContext());
				tv2.setText("游戏2");
				tv2.setWidth(itemWidth);
				GridLayout.Spec rowSpec2 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec2 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params2 = new GridLayout.LayoutParams(rowSpec2, columnSpec2);
				gl_gridlayout.addView(tv2, params2);

				Button tv3 = new Button(getContext());
				tv3.setText("游戏3");
				tv3.setWidth(itemWidth);
				GridLayout.Spec rowSpec3 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec3 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params3 = new GridLayout.LayoutParams(rowSpec3, columnSpec3);
				gl_gridlayout.addView(tv3, params3);

				Button tv4 = new Button(getContext());
				tv4.setText("游戏4");
				tv4.setWidth(itemWidth);
				GridLayout.Spec rowSpec4 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec4 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params4 = new GridLayout.LayoutParams(rowSpec4, columnSpec4);
				gl_gridlayout.addView(tv4, params4);

				Button tv5 = new Button(getContext());
				tv5.setText("游戏5");
				tv5.setWidth(itemWidth);
				GridLayout.Spec rowSpec5 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec5 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params5 = new GridLayout.LayoutParams(rowSpec5, columnSpec5);
				gl_gridlayout.addView(tv5, params5);
				
				Button tv6 = new Button(getContext());
				tv6.setText("游戏6");
				tv6.setWidth(itemWidth);
				GridLayout.Spec rowSpec6 = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec6 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params6 = new GridLayout.LayoutParams(rowSpec6, columnSpec6);
				gl_gridlayout.addView(tv6, params6);

				Button tv7 = new Button(getContext());
				tv7.setText("游戏7");
				tv7.setWidth(itemWidth);
				GridLayout.Spec rowSpec7 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec7 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params7 = new GridLayout.LayoutParams(rowSpec7, columnSpec7);
				gl_gridlayout.addView(tv7, params7);
				
				Button tv8 = new Button(getContext());
				tv8.setText("游戏8");
				tv8.setWidth(itemWidth);
				GridLayout.Spec rowSpec8 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec8 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params8 = new GridLayout.LayoutParams(rowSpec8, columnSpec8);
				gl_gridlayout.addView(tv8, params8);

				Button tv9 = new Button(getContext());
				tv9.setText("游戏9");
				tv9.setWidth(itemWidth);
				GridLayout.Spec rowSpec9 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec9 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params9 = new GridLayout.LayoutParams(rowSpec9, columnSpec9);
				gl_gridlayout.addView(tv9, params9);
				
				Button tv10 = new Button(getContext());
				tv10.setText("游戏10");
				tv10.setWidth(itemWidth);
				GridLayout.Spec rowSpec10 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec10 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params10 = new GridLayout.LayoutParams(rowSpec10, columnSpec10);
				gl_gridlayout.addView(tv10, params10);

				Button tv11 = new Button(getContext());
				tv11.setText("游戏11");
				tv11.setWidth(itemWidth);
				GridLayout.Spec rowSpec11 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec11 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params11 = new GridLayout.LayoutParams(rowSpec11, columnSpec11);
				gl_gridlayout.addView(tv11, params11);
				
				Button tv12 = new Button(getContext());
				tv12.setText("游戏12");
				tv12.setWidth(itemWidth);
				GridLayout.Spec rowSpec12 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec12 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params12 = new GridLayout.LayoutParams(rowSpec12, columnSpec12);
				gl_gridlayout.addView(tv12, params12);

				Button tv13 = new Button(getContext());
				tv13.setText("游戏13");
				tv13.setWidth(itemWidth);
				GridLayout.Spec rowSpec13 = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
				GridLayout.Spec columnSpec13 = GridLayout.spec(GridLayout.UNDEFINED);
				GridLayout.LayoutParams params13 = new GridLayout.LayoutParams(rowSpec13, columnSpec13);
				gl_gridlayout.addView(tv13, params13);
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
