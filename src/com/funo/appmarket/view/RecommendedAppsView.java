package com.funo.appmarket.view;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.AppDetailActivity;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.util.CommonUtils;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class RecommendedAppsView extends GridLayout {

	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	private int mViewHeight;
	
	private boolean setup_flag = false;// 是否已经完成布局
	
	public RecommendedAppsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public RecommendedAppsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RecommendedAppsView(Context context) {
		super(context);
	}

	public void setAppData(List<AppBean> appBeans) {
		this.appBeans = appBeans;
	}
	
	@Override
	protected void onMeasure(int arg0, int arg1) {
		super.onMeasure(arg0, arg1);
		
		mViewHeight = getHeight();
		if (mViewHeight > 0 && !setup_flag) {
			setup_flag = true;
			setup();
		}
	}
	
	private void setup() {
		setRowCount(2);
		setOrientation(GridLayout.HORIZONTAL);
		int itemWidth = mViewHeight / 3 + 55;
		
		int columnCount = 2;
		if (appBeans != null) {
			if (appBeans.size() > 3 ) {
				int size = appBeans.size();
				columnCount = 2 + (int) Math.ceil((size - 3) / 2.0);
			} else {
				switch (appBeans.size()) {
				case 0:
//					appBeans.add(new AppBean("暂无内容", "暂无内容"));
//					appBeans.add(new AppBean("暂无内容", "暂无内容"));
//					appBeans.add(new AppBean("暂无内容", "暂无内容"));
					break;
				case 1:
					appBeans.add(new AppBean("#暂无内容#", "#暂无内容#"));
//					appBeans.add(new AppBean("暂无内容", "暂无内容"));
					break;
				case 2:
//					appBeans.add(new AppBean("暂无内容", "暂无内容"));
					break;
				}
			}
		}
		setColumnCount(columnCount);
		
		for (int i = 0; i < appBeans.size(); i++) {
			AppBean appBean = appBeans.get(i);
			if (i == 0) {
				View v = View.inflate(getContext(), R.layout.gridlayout_item, null);
				v.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						getContext().startActivity(new Intent(getContext(), AppDetailActivity.class));
					}
					
				});
				v.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
				((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
				((TextView) v.findViewById(R.id.sub_title)).setText(appBean.getAppInfo());
				v.setMinimumWidth(itemWidth * 2);
				GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
				GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 2);
				GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
				params.setGravity(Gravity.FILL);
				addView(v, params);
			} else {
				if ((i + 1) < columnCount && appBeans.size() > 3) {
					View v = View.inflate(getContext(), R.layout.gridlayout_item, null);
					v.setFocusable(true);
					v.setFocusableInTouchMode(true);
					v.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							getContext().startActivity(new Intent(getContext(), AppDetailActivity.class));
						}
						
					});
					v.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
					((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
					((TextView) v.findViewById(R.id.sub_title)).setText(appBean.getAppInfo());
					v.setMinimumWidth(itemWidth);
					GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 2);
					GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED);
					GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
					addView(v, params);
				} else {
					View v = View.inflate(getContext(), R.layout.gridlayout_item2, null);
					v.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							getContext().startActivity(new Intent(getContext(), AppDetailActivity.class));
						}
						
					});
					if (TextUtils.equals(appBean.getAppName(), "#暂无内容#")) {
						((TextView) v.findViewById(R.id.title)).setText(null);
						v.findViewById(R.id.content).setBackgroundColor(color.transparent);
						v.findViewById(R.id.app_logo).setVisibility(View.GONE);
						v.findViewById(R.id.recommend_flag).setVisibility(View.GONE);
					} else {
						((TextView) v.findViewById(R.id.title)).setText(appBean.getAppName());
						v.findViewById(R.id.content).setBackgroundColor(CommonUtils.getRandomColor());
					}
					v.setMinimumWidth(itemWidth);
					GridLayout.Spec rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1);
					GridLayout.Spec columnSpec = GridLayout.spec(GridLayout.UNDEFINED);
					GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
					addView(v, params);
				}
			}
		}
	}
	
}
