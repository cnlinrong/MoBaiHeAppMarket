package com.funo.appmarket.activity;

import java.util.List;

import com.funo.appmarket.R;
import com.funo.appmarket.activity.base.BaseActivity;
import com.funo.appmarket.bean.StatusChangeNotify;
import com.funo.appmarket.business.AppBigTypeService;
import com.funo.appmarket.business.AppScoreUpdateService;
import com.funo.appmarket.business.AppSmallTypeService;
import com.funo.appmarket.business.GetTopAppService;
import com.funo.appmarket.business.SearchAppByTypeService;
import com.funo.appmarket.business.SearchAppInfoService;
import com.funo.appmarket.business.StatusChangeNotifyService;
import com.funo.appmarket.business.StatusChangeNotifyService.StatusChangeNotifyCallback;
import com.funo.appmarket.business.SyncEquipmentInfoService;
import com.funo.appmarket.business.define.IStatusChangeNotifyService.StatusChangeNotifyParam;
import com.funo.appmarket.util.ToastUtils;

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
	private AppBigTypeService appBigTypeService;
	private AppSmallTypeService appSmallTypeService;
	private SearchAppByTypeService searchAppByTypeService;
	private SearchAppInfoService searchAppInfoService;
	private AppScoreUpdateService appScoreUpdateService;
	private GetTopAppService getTopAppService;
	
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
//		
//		syncEquipmentInfoService = new SyncEquipmentInfoService(getContext());
//		SyncEquipmentInfoParam syncEquipmentInfoParam = new SyncEquipmentInfoParam();
//		syncEquipmentInfoParam.eqNo = "1";
//		syncEquipmentInfoService.syncEquipmentInfo(syncEquipmentInfoParam, new SyncEquipmentInfoCallback() {
//			
//			@Override
//			public void doCallback() {
//				
//			}
//			
//		});
//		
//		appBigTypeService = new AppBigTypeService(getContext());
//		AppBigTypeParam appBigTypeParam = new AppBigTypeParam();
//		appBigTypeParam.bigTypeValue = null;
//		appBigTypeService.app_bigType(appBigTypeParam, new AppBigTypeCallback() {
//			
//			@Override
//			public void doCallback(List<AppBigType> appBigTypes) {
//				if (appBigTypes != null) {
//					ToastUtils.showShortToast(getContext(), appBigTypes.size() + "");
//				}
//			}
//			
//		});
		
//		appSmallTypeService = new AppSmallTypeService(getContext());
//		AppSmallTypeParam appSmallTypeParam = new AppSmallTypeParam();
//		appSmallTypeParam.smallTypeValue = null;
//		appSmallTypeService.app_smallType(appSmallTypeParam, new AppSmallTypeCallback() {
//			
//			@Override
//			public void doCallback(List<AppSmallType> appSmallTypes) {
//				
//			}
//			
//		});
		
//		searchAppByTypeService = new SearchAppByTypeService(getContext());
//		SearchAppByTypeParam searchAppByTypeParam = new SearchAppByTypeParam();
//		searchAppByTypeParam.smallTypeId = 1;
//		searchAppByTypeParam.orderType = 0;
//		searchAppByTypeParam.pageSize = BaseService.PAGE_SIZE;
//		searchAppByTypeParam.currentPage = 1;
//		searchAppByTypeService.searchAppByType(searchAppByTypeParam, new SearchAppByTypeCallback() {
//			
//			@Override
//			public void doCallback(List<AppBean> appBeans) {
//				
//			}
//			
//		});
		
//		searchAppInfoService = new SearchAppInfoService(getContext());
//		SearchAppInfoParam searchAppInfoParam = new SearchAppInfoParam();
//		searchAppInfoParam.appName = "测试";
//		searchAppInfoParam.appPy = "CS";
//		searchAppInfoParam.pageSize = BaseService.PAGE_SIZE;
//		searchAppInfoParam.currentPage = 1;
//		searchAppInfoService.searchAppInfo(searchAppInfoParam, new SearchAppInfoCallback() {
//			
//			@Override
//			public void doCallback(List<AppBean> appBeans) {
//				
//			}
//			
//		});
		
//		appScoreUpdateService = new AppScoreUpdateService(getContext());
//		AppScoreUpdateParam appScoreUpdateParam = new AppScoreUpdateParam();
//		appScoreUpdateParam.appId = 46;
//		appScoreUpdateParam.eqId = 123;
//		appScoreUpdateParam.score = 3;
//		appScoreUpdateService.appScoreUpdate(appScoreUpdateParam, new AppScoreUpdateCallback() {
//			
//			@Override
//			public void doCallback() {
//				
//			}
//			
//		});
		
//		getTopAppService = new GetTopAppService(getContext());
//		GetTopAppParam getTopAppParam = new GetTopAppParam();
//		getTopAppParam.orderType = 1;
//		getTopAppParam.pageSize = BaseService.PAGE_SIZE;
//		getTopAppParam.currentPage = 1;
//		getTopAppService.getTopApp(getTopAppParam,  new GetTopAppCallback() {
//			
//			@Override
//			public void doCallback(List<AppBean> appBeans) {
//				
//			}
//			
//		});
		
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
					while (true) {
						if (statusChangeNotifyFlag) {
							break;
						}
					}
					startActivity(new Intent(getContext(), MainActivity.class));
					finish();
				}
			}
			
		};
		pb.post(r);
	}

}
