package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.bean.LawyerBean;
import com.funo.appmarket.business.TestService;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private TestService testService;
	
	private List<LawyerBean> lawyers = new ArrayList<LawyerBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		testService = new TestService();
		
		setContentView(R.layout.activity_main);
		
		lawyers = testService.getLawyers();
	}

}
