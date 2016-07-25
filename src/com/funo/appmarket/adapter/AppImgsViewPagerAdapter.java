package com.funo.appmarket.adapter;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.funo.appmarket.constant.Constants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AppImgsViewPagerAdapter extends FragmentPagerAdapter {

	private Context mContext;
	
	private List<String> appImgs = new ArrayList<String>();
	
	public AppImgsViewPagerAdapter(FragmentManager fm, Context context, List<String> appImgs) {
		super(fm);
		
		this.mContext = context;
		this.appImgs = appImgs;
	}

	@Override
	public Fragment getItem(int position) {
		return new MyFragment(position);
	}

	@Override
	public int getCount() {
		return appImgs.size();
	}

	class MyFragment extends Fragment {

		int position;
		
		public MyFragment(int position) {
			this.position = position;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			ImageView iv = new ImageView(mContext);
			iv.setLayoutParams(layoutParams);
			iv.setScaleType(ScaleType.FIT_XY);
			Glide.with(mContext).load(Constants.IMAGE_URL + appImgs.get(position)).into(iv);
			return iv;
		}
		
	}
	
}
