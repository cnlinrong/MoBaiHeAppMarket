package com.funo.appmarket.activity;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends BaseActivity {

	private ProgressBar pb;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_loading);
		
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				pb.setProgress(pb.getProgress() + 2);
				if (pb.getProgress() < 100) {
					pb.postDelayed(this, 50);
				} else {
					startActivity(new Intent(getContext(), MainActivity.class));
					finish();
				}
			}
			
		};
		pb.post(r);
	}

}
