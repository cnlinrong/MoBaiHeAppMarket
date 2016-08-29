package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.adapter.KeyboardGridViewAdapter;
import com.funo.appmarket.adapter.PopularAppsGridViewAdapter;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.business.RecAppInfoService;
import com.funo.appmarket.business.RecAppInfoService.RecAppInfoCallback;
import com.funo.appmarket.business.SearchAppInfoService;
import com.funo.appmarket.business.SearchAppInfoService.SearchAppInfoCallback;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IRecAppInfoService.RecAppInfoReqParam;
import com.funo.appmarket.business.define.ISearchAppInfoService.SearchAppInfoParam;
import com.funo.appmarket.util.AnimationUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class SearchActivity extends BaseActivity {

	private float originalWidth = 1.0f;
	private float originalHeight = 1.0f;
	private float targetWidth = 1.4f;
	private float targetHeight = 1.4f;
	private long duration = 200;
	
	private SearchAppInfoService searchAppInfoService;
	private RecAppInfoService recAppInfoService;
	
	private View mOldView;
	private TextView label_tv;
	
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
		recAppInfoService = new RecAppInfoService(getContext());
		
		setContentView(R.layout.activity_search);
		
        label_tv = (TextView) findViewById(R.id.label_tv);
        
		keyboard = (GridView) findViewById(R.id.keyboard);
		popular_apps = (GridView) findViewById(R.id.popular_apps);
		popular_apps.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (view != null && parent.isFocused() && view != mOldView) {
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
		popular_apps.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					View selectedView = popular_apps.getSelectedView();
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
		btn_123.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		btn_123.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				keyboardGridViewAdapter.switchInput(keyboard, btn_123);
			}
			
		});
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		btn_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(search_input.getText())) {
					search_input.setText(null);
				}
			}
			
		});
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					AnimationUtils.scaleAnim(v, originalWidth, originalHeight, targetWidth, targetHeight, duration);
				} else {
					AnimationUtils.scaleAnim(v, targetWidth, targetHeight, originalWidth, originalHeight, duration);
				}
			}
			
		});
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(search_input.getText())) {
					search_input.setText(search_input.getText().subSequence(0, search_input.getText().length() - 1));
				}
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
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		search(search_input.getText().toString());
	}
	
	private void search(String keyword) {
		if (TextUtils.isEmpty(keyword)) {
			label_tv.setText("热门应用");
			RecAppInfoReqParam recAppInfoReqParam = new RecAppInfoReqParam();
			recAppInfoReqParam.type = 0;
			recAppInfoReqParam.pageSize = 6;
			recAppInfoReqParam.currentPage = 1;
			recAppInfoService.recAppInfo(recAppInfoReqParam, new RecAppInfoCallback() {

				@Override
				public void doCallback(List<AppBean> appData) {
					if (appData != null) {
						SearchActivity.this.appData = appData;
						popularAppsGridViewAdapter.setData(appData);
					}
				}

			});
		} else {
			label_tv.setText("搜索结果");
			SearchAppInfoParam searchAppInfoParam = new SearchAppInfoParam();
			searchAppInfoParam.appPy = keyword;
			searchAppInfoParam.pageSize = BaseService.PAGE_SIZE;
			searchAppInfoParam.currentPage = 1;
			searchAppInfoService.searchAppInfo(searchAppInfoParam, new SearchAppInfoCallback() {
				
				@Override
				public void doCallback(List<AppBean> appBeans) {
					if (appBeans != null) {
						SearchActivity.this.appData = appBeans;
						popularAppsGridViewAdapter.setData(appBeans);
					}
				}
				
			});
		}
	}
	
}
