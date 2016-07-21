package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import org.evilbinary.tv.widget.BorderView;
import org.evilbinary.tv.widget.TvHorizontalScrollView;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.NavListAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.business.RecAppInfoService;
import com.funo.appmarket.business.RecAppInfoService.RecAppInfoCallback;
import com.funo.appmarket.datasource.HomeTemplate1;
import com.funo.appmarket.datasource.HomeTemplate2;
import com.funo.appmarket.datasource.HomeTemplate3;
import com.funo.appmarket.datasource.IHomeTemplate;
import com.funo.appmarket.util.CommonUtils;
import com.gridbuilder.GridBuilder;
import com.gridbuilder.GridItem;
import com.gridbuilder.GridViewHolder;
import com.gridbuilder.calculator.HorizontalPositionCalculator;
import com.gridbuilder.listener.OnViewCreateCallBack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SubActivity extends BaseActivity {

	private RecAppInfoService appService;
	
	private TvHorizontalScrollView hsv;
	private ListView navList;
	private View search;

	private GridLayout gl_gridlayout;

	private List<AppBean> appBeans = new ArrayList<AppBean>();

	private NavListAdapter navListAdapter;
	private List<NavItem> navItems = new ArrayList<NavItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sub);

		initView();

		appService = new RecAppInfoService(getContext());
		appService.recAppInfo(new RecAppInfoCallback() {

			@Override
			public void doCallback(List<AppBean> appData) {
				if (appData != null) {
					appBeans = appData;
					refreshGridData();
				}
			}

		});
		
		search = findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getContext(), SearchActivity.class));
			}

		});

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
		appBeans.add(new AppBean("疯狂桌球", "每天疯狂一下"));

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

		navList.post(new Runnable() {

			@Override
			public void run() {
				navList.requestFocus();
			}

		});
	}

	private void initView() {
		hsv = (TvHorizontalScrollView) findViewById(R.id.hsv);
		gl_gridlayout = (GridLayout) findViewById(R.id.gl_gridlayout);

		BorderView border = new BorderView(this);
		border.setBackgroundResource(R.drawable.test_rectangle);
		border.attachTo(gl_gridlayout);
	}

	private void refreshGridData() {
		hsv.post(new Runnable() {

			@Override
			public void run() {
				GridViewHolder holder = new GridViewHolder(gl_gridlayout);

//				IHomeTemplate homeTemplate = new HomeTemplate1(appBeans, hsv.getHeight());
				IHomeTemplate homeTemplate = new HomeTemplate2(appBeans, hsv.getHeight());
//				IHomeTemplate homeTemplate = new HomeTemplate3(appBeans, hsv.getHeight());
				GridBuilder.newInstance(getContext(), gl_gridlayout).setScaleAnimationDuration(200)
						.setOrientation(homeTemplate.getOrientation())
						.setRowCount(homeTemplate.getRowCount())
						.setMargin(5).setColumnCount(homeTemplate.getColumnCount())
						.setGridItemList(homeTemplate.getGridData()).setViewHolder(holder)
						.setOnCreateViewCallBack(new OnViewCreateCallBack() {

					@Override
					public View onViewCreate(LayoutInflater inflater, View convertView, GridItem gridItem) {
						AppBean appBean = (AppBean) gridItem.getData();

						View v = null;
						if (null == convertView) {
							if (gridItem.getView_type() == 0) {
								v = View.inflate(getContext(), R.layout.gridlayout_item, null);
							} else if (gridItem.getView_type() == 1) {
								v = View.inflate(getContext(), R.layout.gridlayout_item2, null);
							} else if (gridItem.getView_type() == 2) {
								v = View.inflate(getContext(), R.layout.gridlayout_item3, null);
							}
							v.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									getContext().startActivity(new Intent(getContext(), AppDetailActivity.class));
								}

							});
						} else {
							v = convertView;
						}
						if (appBean != null) {
							if (gridItem.getView_type() == 0) {
								((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
								((TextView) v.findViewById(R.id.sub_title)).setText(appBean.getAppInfo());
							} else if (gridItem.getView_type() == 1) {
								((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
							} else if (gridItem.getView_type() == 2) {
								((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
								((TextView) v.findViewById(R.id.sub_title)).setText(appBean.getAppInfo());
							}
						}
						v.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
						v.setFocusable(true);
						return v;
					}

				}).build();
			}

		});
	}
	
}
