package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.model.AppInfo;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ViewHolderUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InstalledAppsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<AppInfo> appInfos = new ArrayList<AppInfo>();
	
	public InstalledAppsGridViewAdapter(Context context, List<AppInfo> appInfos) {
		this.mContext = context;
		this.appInfos = appInfos;
	}
	
	@Override
	public int getCount() {
		return appInfos.size();
	}

	@Override
	public AppInfo getItem(int position) {
		return appInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.gridview_item_apps, null);
		}
		ImageView appLogo = ViewHolderUtils.get(convertView, R.id.appLogo);
		TextView appName = ViewHolderUtils.get(convertView, R.id.appName);
		AppInfo appInfo = getItem(position);
		Glide.with(mContext).load(Constants.IMAGE_URL + appInfo.getAppLogo()).into(appLogo);
		appName.setText(appInfo.getAppName());
		convertView.setBackgroundColor(CommonUtils.getRandomColor());
		return convertView;
	}

	public void setData(List<AppInfo> appInfos) {
		this.appInfos = appInfos;
		notifyDataSetChanged();
	}
	
}
