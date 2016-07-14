package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ViewHolderUtils;
import com.funo.appmarket.view.CustomImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopularAppsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	public PopularAppsGridViewAdapter(Context context, List<AppBean> appBeans) {
		this.mContext = context;
		this.appBeans = appBeans;
	}
	
	@Override
	public int getCount() {
		return appBeans.size();
	}

	@Override
	public AppBean getItem(int position) {
		return appBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.gridview_item_popular_apps, null);
		}
		CustomImageView appLogo = ViewHolderUtils.get(convertView, R.id.appLogo);
		TextView appName = ViewHolderUtils.get(convertView, R.id.appName);
		AppBean appBean = getItem(position);
		appName.setText(appBean.getAppName());
		convertView.setBackgroundColor(CommonUtils.getRandomColor());
		return convertView;
	}

}
