package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ViewHolderUtils;
import com.funo.appmarket.view.RatingView;
import com.makeramen.roundedimageview.RoundedImageView;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopularAppsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<AppBean> appBeans = new ArrayList<AppBean>();
	
	private SparseIntArray colorMap = new SparseIntArray();
	
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
		TextView size_downloadnum = ViewHolderUtils.get(convertView, R.id.size_downloadnum);
		RatingView ratingView = ViewHolderUtils.get(convertView, R.id.ratingView);
		RoundedImageView appLogo = ViewHolderUtils.get(convertView, R.id.appLogo);
		TextView appName = ViewHolderUtils.get(convertView, R.id.appName);
		View contentView = ViewHolderUtils.get(convertView, R.id.contentView);
		AppBean appBean = getItem(position);
		appName.setText(appBean.getAppName());
		
		int appId = (int) appBean.getAppId();
		int color = colorMap.get(appId, -1);
		if (color == -1) {
			int randomColor = CommonUtils.getRandomColor();
			contentView.setBackgroundColor(randomColor);
			colorMap.append(appId, randomColor);
		} else {
			contentView.setBackgroundColor(color);
		}
		
		ratingView.setScore(appBean.getScore());
		size_downloadnum.setText(appBean.getAppSize() + "MB | " + appBean.getDownnum());
		Glide.with(mContext).load(Constants.IMAGE_URL + appBean.getAppLogo()).into(appLogo);
		return convertView;
	}

	public void setData(List<AppBean> appBeans) {
		this.appBeans = appBeans;
		notifyDataSetChanged();
	}
	
}
