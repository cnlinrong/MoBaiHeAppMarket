package com.funo.appmarket.business;

import com.funo.appmarket.bean.base.BaseBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ISyncEquipmentInfoService;
import com.funo.appmarket.business.define.ISyncEquipmentInfoService.SyncEquipmentInfoParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncEquipmentInfoService extends BaseService {

	private ISyncEquipmentInfoService iSyncEquipmentInfoService;

	public SyncEquipmentInfoService(Context context) {
		super(context);

		iSyncEquipmentInfoService = retrofit.create(ISyncEquipmentInfoService.class);
	}

	/**
	 * 设备信息同步
	 * 
	 * @param syncEquipmentInfoParam
	 * @param syncEquipmentInfoCallback
	 */
	public void syncEquipmentInfo(SyncEquipmentInfoParam syncEquipmentInfoParam, final SyncEquipmentInfoCallback syncEquipmentInfoCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iSyncEquipmentInfoService.syncEquipmentInfo(reqNo, CommonUtils.signReq(reqNo, gson.toJson(syncEquipmentInfoParam)), syncEquipmentInfoParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				BaseBusinessBean<?> baseBusinessBean = handleResponse(response, BaseBusinessBean.class);
				if (baseBusinessBean != null) {
					if (syncEquipmentInfoCallback != null) {
						syncEquipmentInfoCallback.doCallback();
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (syncEquipmentInfoCallback != null) {
					syncEquipmentInfoCallback.doCallback();
				}
			}

		});
	}

	public interface SyncEquipmentInfoCallback {

		public void doCallback();

	}

}
