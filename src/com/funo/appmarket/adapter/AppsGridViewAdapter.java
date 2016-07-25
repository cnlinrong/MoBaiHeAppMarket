package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ViewHolderUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	public AppsGridViewAdapter(Context context, List<AppBean> appBeans) {
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
			convertView = View.inflate(mContext, R.layout.gridview_item_apps, null);
		}
		ImageView appLogo = ViewHolderUtils.get(convertView, R.id.appLogo);
		TextView appName = ViewHolderUtils.get(convertView, R.id.appName);
		AppBean appBean = getItem(position);
		Glide.with(mContext).load(Constants.IMAGE_URL + appBean.getAppLogo()).into(appLogo);
		appName.setText(appBean.getAppName());
		convertView.setBackgroundColor(CommonUtils.getRandomColor());
		return convertView;
	}

	public void setData(List<AppBean> appData) {
		this.appBeans = appData;
		notifyDataSetChanged();
	}
	
}
