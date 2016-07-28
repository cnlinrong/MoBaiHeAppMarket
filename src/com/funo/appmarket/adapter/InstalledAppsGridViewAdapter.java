package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.model.AppModel;
import com.funo.appmarket.util.CommonUtils;
import com.funo.appmarket.util.ViewHolderUtils;
import com.funo.appmarket.view.RatingView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InstalledAppsGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<AppModel> appModels = new ArrayList<AppModel>();
	
	public InstalledAppsGridViewAdapter(Context context, List<AppModel> appModels) {
		this.mContext = context;
		this.appModels = appModels;
	}
	
	@Override
	public int getCount() {
		return appModels.size();
	}

	@Override
	public AppModel getItem(int position) {
		return appModels.get(position);
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
		RatingView ratingView = ViewHolderUtils.get(convertView, R.id.ratingView);
		ImageView tag_img = ViewHolderUtils.get(convertView, R.id.tag_img);
		ImageView appLogo = ViewHolderUtils.get(convertView, R.id.appLogo);
		TextView appName = ViewHolderUtils.get(convertView, R.id.appName);
		AppModel appModel = getItem(position);
		Glide.with(mContext).load(Constants.IMAGE_URL + appModel.getAppLogo()).into(appLogo);
		appName.setText(appModel.getAppName());
		ratingView.setScore(appModel.getScore());
		convertView.setBackgroundColor(CommonUtils.getRandomColor());
		int tag = 0;
		try {
			tag = Integer.parseInt(appModel.getTag());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		switch (tag) {// 标签类型 0:无标签 1:精品 2:NEW 3:HOT
		case 0:
			tag_img.setVisibility(View.GONE);
			break;
		case 1:
			tag_img.setVisibility(View.VISIBLE);
			tag_img.setImageResource(R.drawable.recommend_best);
			break;
		case 2:
			tag_img.setVisibility(View.VISIBLE);
			tag_img.setImageResource(R.drawable.recommend_new);
			break;
		case 3:
			tag_img.setVisibility(View.VISIBLE);
			tag_img.setImageResource(R.drawable.recommend_hot);
			break;
		}
		return convertView;
	}

	public void setData(List<AppModel> appInfos) {
		this.appModels = appInfos;
		notifyDataSetChanged();
	}
	
}
