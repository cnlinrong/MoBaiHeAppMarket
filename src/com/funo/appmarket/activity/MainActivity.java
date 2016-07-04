package com.funo.appmarket.activity;

import java.util.ArrayList;
import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.bean.LawyerBean;
import com.funo.appmarket.business.TestService;
import com.funo.appmarket.business.TestService.GetLawyersCallback;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	
	private TestService testService;
	
	private List<LawyerBean> lawyerData = new ArrayList<LawyerBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		testService = new TestService();
		
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.textView);
		
		testService.getLawyers(new GetLawyersCallback() {
			
			@Override
			public void doCallback(List<LawyerBean> lawyers) {
				lawyerData = lawyers;
				textView.setText("结果数：" + lawyerData.size());
			}
			
		});
	}

}
