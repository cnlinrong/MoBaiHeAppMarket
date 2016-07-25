package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.R;
import com.funo.appmarket.bean.NavItem;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.util.ViewHolderUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavListAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<NavItem> navItems = new ArrayList<NavItem>();
	
	private int itemHeight;
	
	public NavListAdapter(Context context, List<NavItem> navItems, int itemHeight) {
		this.mContext = context;
		this.navItems = navItems;
		this.itemHeight = itemHeight;
	}
	
	@Override
	public int getCount() {
		return navItems.size();
	}

	@Override
	public NavItem getItem(int position) {
		return navItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.list_item_nav_list, null);
			convertView.setMinimumHeight(itemHeight);
		}
		ImageView image = ViewHolderUtils.get(convertView, R.id.image);
		TextView name = ViewHolderUtils.get(convertView, R.id.name);
		NavItem navItem = getItem(position);
		name.setText(navItem.getLabel());
		Glide.with(mContext).load(Constants.IMAGE_URL + navItem.getLogo()).into(image);
		return convertView;
	}

}
