package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.SyncAppDownBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ISyncAppDownService;
import com.funo.appmarket.business.define.ISyncAppDownService.SyncAppDownParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncAppDownService extends BaseService {

	private ISyncAppDownService iSyncAppDownService;

	public SyncAppDownService(Context context) {
		super(context);

		iSyncAppDownService = retrofit.create(ISyncAppDownService.class);
	}

	/**
	 * 应用下载次数同步
	 * 
	 * @param syncAppDownParam
	 * @param syncAppDownCallback
	 */
	public void syncAppDown(SyncAppDownParam syncAppDownParam, final SyncAppDownCallback syncAppDownCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iSyncAppDownService.syncAppDown(reqNo, CommonUtils.signReq(reqNo, gson.toJson(syncAppDownParam)), syncAppDownParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				SyncAppDownBusinessBean syncAppDownBusinessBean = handleResponse(response, SyncAppDownBusinessBean.class, SHOW_ERROR_TOAST);
				if (syncAppDownBusinessBean != null) {
					if (syncAppDownCallback != null) {
						syncAppDownCallback.doCallback(syncAppDownBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (syncAppDownCallback != null) {
					syncAppDownCallback.doCallback(null);
				}
			}

		});
	}

	public interface SyncAppDownCallback {

		public void doCallback(List<AppBean> appBeans);

	}

}
