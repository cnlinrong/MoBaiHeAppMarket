package com.funo.appmarket.activity;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.bean.StatusChangeNotify;
import com.funo.appmarket.business.StatusChangeNotifyService;
import com.funo.appmarket.business.StatusChangeNotifyService.StatusChangeNotifyCallback;
import com.funo.appmarket.business.SyncEquipmentInfoService;
import com.funo.appmarket.business.SyncEquipmentInfoService.SyncEquipmentInfoCallback;
import com.funo.appmarket.business.define.IStatusChangeNotifyService.StatusChangeNotifyParam;
import com.funo.appmarket.business.define.ISyncEquipmentInfoService.SyncEquipmentInfoParam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends BaseActivity {

	private ProgressBar pb;
	private TextView tv;
	
	private boolean statusChangeNotifyFlag = false;// 调用状态改变通知接口是否完成
	
	private StatusChangeNotifyService statusChangeNotifyService;
	private SyncEquipmentInfoService syncEquipmentInfoService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		statusChangeNotifyService = new StatusChangeNotifyService(getContext());
		StatusChangeNotifyParam statusChangeNotifyParam = new StatusChangeNotifyParam();
		statusChangeNotifyService.statusChangeNotify(statusChangeNotifyParam, new StatusChangeNotifyCallback() {
			
			@Override
			public void doCallback(List<StatusChangeNotify> statusChangeNotifies) {
				if (statusChangeNotifies != null) {
					if (!statusChangeNotifies.isEmpty()) {
						StatusChangeNotify statusChangeNotify = statusChangeNotifies.get(0);
						sys_sp.edit().putBoolean("isTypeChage", statusChangeNotify.isTypeChage()).apply();
						sys_sp.edit().putInt("templateUsedId", statusChangeNotify.getTemplateUsedId()).apply();
					}
				}
				
				statusChangeNotifyFlag = true;
			}
			
		});
		
		syncEquipmentInfoService = new SyncEquipmentInfoService(getContext());
		SyncEquipmentInfoParam syncEquipmentInfoParam = new SyncEquipmentInfoParam();
		syncEquipmentInfoParam.eqNo = "1";
		syncEquipmentInfoService.syncEquipmentInfo(syncEquipmentInfoParam, new SyncEquipmentInfoCallback() {
			
			@Override
			public void doCallback() {
				
			}
			
		});
		
		setContentView(R.layout.activity_loading);
		
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				if (pb.getProgress() < 100) {
					pb.setProgress(pb.getProgress() + 2);
				}
				if (pb.getProgress() == 100 && statusChangeNotifyFlag) {
					startActivity(new Intent(getContext(), MainActivity.class));
					finish();
				} else {
					pb.postDelayed(this, 50);
				}
			}
			
		};
		pb.post(r);
	}

}
