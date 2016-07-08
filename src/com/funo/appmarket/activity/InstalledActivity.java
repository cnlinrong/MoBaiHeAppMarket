package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.R.id;
import com.funo.appmarket.R.layout;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.InstalledGridViewAdapter;
import com.funo.appmarket.bean.AppBean;

import android.os.Bundle;
import android.widget.GridView;

public class InstalledActivity extends BaseActivity {

	private GridView installed_list;
	private InstalledGridViewAdapter installedGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_installed);
		
		installed_list = (GridView) findViewById(R.id.installed_list);
		AppBean appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", null, 0, 0);
		appBeans.add(appBean);
		installedGridViewAdapter = new InstalledGridViewAdapter(getContext(), appBeans);
		installed_list.setAdapter(installedGridViewAdapter);
	}

}
