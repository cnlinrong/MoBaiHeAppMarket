package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.KeyboardGridViewAdapter;
import com.funo.appmarket.adapter.PopularAppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.SearchAppInfoService;
import com.funo.appmarket.business.SearchAppInfoService.SearchAppInfoCallback;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ISearchAppInfoService.SearchAppInfoParam;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class SearchActivity extends BaseActivity {

	private SearchAppInfoService searchAppInfoService;
	
	private MainUpView mainUpView1;
	private View mOldView;
	
	private GridView keyboard;
	private GridView popular_apps;
	private EditText search_input;
	private Button btn_123;
	private Button btn_clear;
	private Button btn_delete;
	
	private KeyboardGridViewAdapter keyboardGridViewAdapter;
	private PopularAppsGridViewAdapter popularAppsGridViewAdapter;
	
	private List<AppBean> appData = new ArrayList<AppBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchAppInfoService = new SearchAppInfoService(getContext());
		
		setContentView(R.layout.activity_search);
		
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
        effectNoDrawBridge.setTranDurAnimTime(200);
        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
        mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(2);
		
		keyboard = (GridView) findViewById(R.id.keyboard);
		popular_apps = (GridView) findViewById(R.id.popular_apps);
		popular_apps.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * 这里注意要加判断是否为NULL.
				 * 因为在重新加载数据以后会出问题.
				 */
				if (view != null) {
					view.bringToFront();
					mainUpView1.setFocusView(view, mOldView, 1.05f);
				}
				mOldView = view;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				mainUpView1.setVisibility(View.INVISIBLE);
			}
			
		});
		search_input = (EditText) findViewById(R.id.search_input);
		search_input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				search(s.toString());
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
		
		popularAppsGridViewAdapter = new PopularAppsGridViewAdapter(getContext(), appData);
		popular_apps.setAdapter(popularAppsGridViewAdapter);
		popular_apps.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getContext(), AppDetailActivity.class);
				intent.putExtra("selectedApp", popularAppsGridViewAdapter.getItem(position));
				startActivity(intent);				
			}
			
		});
		
		keyboard.post(new Runnable() {

			@Override
			public void run() {
				keyboard.requestFocus();
			}

		});
		
		search("测");
	}

	private void search(String keyword) {
		SearchAppInfoParam searchAppInfoParam = new SearchAppInfoParam();
		searchAppInfoParam.appName = keyword;
		searchAppInfoParam.appPy = keyword;
		searchAppInfoParam.pageSize = BaseService.PAGE_SIZE;
		searchAppInfoParam.currentPage = 1;
		searchAppInfoService.searchAppInfo(searchAppInfoParam, new SearchAppInfoCallback() {
			
			@Override
			public void doCallback(List<AppBean> appBeans) {
				if (appBeans != null) {
					appData = appBeans;
					popularAppsGridViewAdapter.setData(appBeans);
				}
			}
			
		});
	}
	
}
