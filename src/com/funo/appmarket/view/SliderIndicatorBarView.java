package com.funo.appmarket.view;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SliderIndicatorBarView extends LinearLayout {

	private List<ImageView> indicators = new ArrayList<ImageView>();
	
	public SliderIndicatorBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SliderIndicatorBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SliderIndicatorBarView(Context context) {
		super(context);
	}

	public void setNum(int num) {
		for (int i = 0; i < num; i++) {
			ImageView iv = new ImageView(getContext());
			if (i == 0) {
				iv.setImageResource(R.drawable.slider_img_01);
			} else {
				iv.setImageResource(R.drawable.slider_img_02);
			}
			LayoutParams layoutParams = new LayoutParams(10, 10);
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			addView(iv, layoutParams);
			indicators.add(iv);
		}
	}
	
	public void selectIndicator(int index) {
		resetIndicators();
		
		if (!indicators.isEmpty() && index < indicators.size()) {
			ImageView indicator = indicators.get(index);
			indicator.setImageResource(R.drawable.slider_img_01);
		}
	}
	
	private void resetIndicators() {
		for (ImageView indicator : indicators) {
			indicator.setImageResource(R.drawable.slider_img_02);
		}
	}
	
}
