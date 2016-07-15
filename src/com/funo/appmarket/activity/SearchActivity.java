package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.KeyboardGridViewAdapter;
import com.funo.appmarket.adapter.PopularAppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.util.ToastUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class SearchActivity extends BaseActivity {

	private GridView keyboard;
	private GridView popular_apps;
	private EditText search_input;
	private Button btn_123;
	private Button btn_clear;
	private Button btn_delete;
	
	private KeyboardGridViewAdapter keyboardGridViewAdapter;
	private PopularAppsGridViewAdapter popularAppsGridViewAdapter;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search);
		
		keyboard = (GridView) findViewById(R.id.keyboard);
		popular_apps = (GridView) findViewById(R.id.popular_apps);
		search_input = (EditText) findViewById(R.id.search_input);
		search_input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ToastUtils.showShortToast(getContext(), s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
			
		});
		btn_123 = (Button) findViewById(R.id.btn_123);
		btn_123.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				keyboardGridViewAdapter.switchInput(keyboard, btn_123);
			}
			
		});
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search_input.setText(null);
			}
			
		});
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search_input.setText(search_input.getText().subSequence(0, search_input.getText().length() - 1));
			}
			
		});
		
		keyboardGridViewAdapter = new KeyboardGridViewAdapter(getContext());
		keyboard.setAdapter(keyboardGridViewAdapter);
		keyboard.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				search_input.setText(search_input.getText() + keyboardGridViewAdapter.getItem(position));
			}
			
		});
		
		AppBean appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		appBean = new AppBean("室内设计", "暂无内容");
		appBeans.add(appBean);
		popularAppsGridViewAdapter = new PopularAppsGridViewAdapter(getContext(), appBeans);
		popular_apps.setAdapter(popularAppsGridViewAdapter);
		popular_apps.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(getContext(), AppDetailActivity.class));				
			}
			
		});
	}

}
