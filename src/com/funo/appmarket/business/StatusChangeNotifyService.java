package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.StatusChangeNotify;
import com.funo.appmarket.bean.StatusChangeNotifyBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IStatusChangeNotifyService;
import com.funo.appmarket.business.define.IStatusChangeNotifyService.StatusChangeNotifyParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusChangeNotifyService extends BaseService {

	private IStatusChangeNotifyService iStatusChangeNotifyService;

	public StatusChangeNotifyService(Context context) {
		super(context);

		iStatusChangeNotifyService = retrofit.create(IStatusChangeNotifyService.class);
	}

	/**
	 * 状态变更通知
	 * 
	 * @param statusChangeNotifyParam
	 * @param statusChangeNotifyCallback
	 */
	public void statusChangeNotify(StatusChangeNotifyParam statusChangeNotifyParam, final StatusChangeNotifyCallback statusChangeNotifyCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iStatusChangeNotifyService.statusChangeNotify(reqNo, CommonUtils.signReq(reqNo, gson.toJson(statusChangeNotifyParam)), statusChangeNotifyParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				StatusChangeNotifyBusinessBean statusChangeNotifyBusinessBean = handleResponse(response, StatusChangeNotifyBusinessBean.class);
				if (statusChangeNotifyBusinessBean != null) {
					if (statusChangeNotifyCallback != null) {
						statusChangeNotifyCallback.doCallback(statusChangeNotifyBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (statusChangeNotifyCallback != null) {
					statusChangeNotifyCallback.doCallback(null);
				}
			}

		});
	}

	public interface StatusChangeNotifyCallback {

		public void doCallback(List<StatusChangeNotify> statusChangeNotifies);

	}

}
