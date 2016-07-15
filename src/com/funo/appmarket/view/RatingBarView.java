package com.funo.appmarket.view;

import com.funo.appmarket.R;
import com.funo.appmarket.util.ToastUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class RatingBarView extends LinearLayout implements OnClickListener {

	private int focused_index = 1;

	public RatingBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init();
	}

	public RatingBarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public RatingBarView(Context context) {
		super(context);

		init();
	}

	private void init() {
		final Button btn1 = new Button(getContext());
		btn1.setBackgroundResource(R.drawable.rate_big_01);
		LayoutParams layoutParams1 = new LayoutParams(60, 60);
		addView(btn1, layoutParams1);

		final Button btn2 = new Button(getContext());
		btn2.setBackgroundResource(R.drawable.rate_big_02);
		LayoutParams layoutParams2 = new LayoutParams(60, 60);
		layoutParams2.leftMargin = 15;
		layoutParams2.rightMargin = 15;
		addView(btn2, layoutParams2);

		final Button btn3 = new Button(getContext());
		btn3.setBackgroundResource(R.drawable.rate_big_02);
		LayoutParams layoutParams3 = new LayoutParams(60, 60);
		addView(btn3, layoutParams3);

		final Button btn4 = new Button(getContext());
		btn4.setBackgroundResource(R.drawable.rate_big_02);
		LayoutParams layoutParams4 = new LayoutParams(60, 60);
		layoutParams4.leftMargin = 15;
		layoutParams4.rightMargin = 15;
		addView(btn4, layoutParams4);

		final Button btn5 = new Button(getContext());
		btn5.setBackgroundResource(R.drawable.rate_big_02);
		LayoutParams layoutParams5 = new LayoutParams(60, 60);
		addView(btn5, layoutParams5);

		btn1.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					focused_index = 1;
					btn1.setBackgroundResource(R.drawable.rate_big_01);
					btn2.setBackgroundResource(R.drawable.rate_big_02);
				}
			}

		});
		btn2.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					focused_index = 2;
					btn2.setBackgroundResource(R.drawable.rate_big_01);
					btn3.setBackgroundResource(R.drawable.rate_big_02);
				}
			}

		});
		btn3.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					focused_index = 3;
					btn3.setBackgroundResource(R.drawable.rate_big_01);
					btn4.setBackgroundResource(R.drawable.rate_big_02);
				}
			}

		});
		btn4.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					focused_index = 4;
					btn4.setBackgroundResource(R.drawable.rate_big_01);
					btn5.setBackgroundResource(R.drawable.rate_big_02);
				}
			}

		});
		btn5.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					focused_index = 5;
					btn5.setBackgroundResource(R.drawable.rate_big_01);
				}
			}

		});

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ToastUtils.showShortToast(getContext(), focused_index + "");
	}

}
