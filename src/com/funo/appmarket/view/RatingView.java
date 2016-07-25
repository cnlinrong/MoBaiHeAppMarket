package com.funo.appmarket.view;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RatingView extends LinearLayout {

	private float score = 0;
	
	private int star_width = 15;
	private int star_height = 15;
	private int margin_left = 2;
	private int margin_right = 2;
	
	private List<ImageView> stars = new ArrayList<ImageView>();
	
	public RatingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		init();
	}

	public RatingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}

	public RatingView(Context context) {
		super(context);
		
		init();
	}

	private void init() {
		final ImageView star1 = new ImageView(getContext());
		star1.setImageResource(getStarImage(1));
		LayoutParams layoutParams1 = new LayoutParams(star_width, star_height);
		addView(star1, layoutParams1);
		stars.add(star1);

		final ImageView star2 = new ImageView(getContext());
		star2.setImageResource(getStarImage(2));
		LayoutParams layoutParams2 = new LayoutParams(star_width, star_height);
		layoutParams2.leftMargin = margin_left;
		layoutParams2.rightMargin = margin_right;
		addView(star2, layoutParams2);
		stars.add(star2);

		final ImageView star3 = new ImageView(getContext());
		star3.setImageResource(getStarImage(3));
		LayoutParams layoutParams3 = new LayoutParams(star_width, star_height);
		addView(star3, layoutParams3);
		stars.add(star3);

		final ImageView star4 = new ImageView(getContext());
		star4.setBackgroundResource(getStarImage(4));
		star4.setImageResource(getStarImage(4));
		LayoutParams layoutParams4 = new LayoutParams(star_width, star_height);
		layoutParams4.leftMargin = margin_left;
		layoutParams4.rightMargin = margin_right;
		addView(star4, layoutParams4);
		stars.add(star4);

		final ImageView star5 = new ImageView(getContext());
		star5.setImageResource(getStarImage(5));
		LayoutParams layoutParams5 = new LayoutParams(star_width, star_height);
		addView(star5, layoutParams5);
		stars.add(star5);
	}
	
	public void setScore(float score) {
		this.score = score;
		reset();
		setup();
	}

	private int getStarImage(int index) {
		if (Math.ceil(score) > 5) {
			score = 5;
		}
		if (index <= score) {
			return R.drawable.rate_star_01;
		} else {
			return R.drawable.rate_star_02;
		}
	}
	
	private void reset() {
		for (ImageView star : stars) {
			star.setImageResource(R.drawable.rate_star_02);
		}
	}
	
	private void setup() {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).setImageResource(getStarImage(i));
		}
	}
	
}
