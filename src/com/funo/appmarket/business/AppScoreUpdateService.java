package com.funo.appmarket.business;

import com.funo.appmarket.bean.base.BaseBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IAppScoreUpdateService;
import com.funo.appmarket.business.define.IAppScoreUpdateService.AppScoreUpdateParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppScoreUpdateService extends BaseService {

	private IAppScoreUpdateService iAppScoreUpdateService;

	public AppScoreUpdateService(Context context) {
		super(context);

		iAppScoreUpdateService = retrofit.create(IAppScoreUpdateService.class);
	}

	/**
	 * 应用评分接口
	 * 
	 * @param appScoreUpdateParam
	 * @param appScoreUpdateCallback
	 */
	public void appScoreUpdate(AppScoreUpdateParam appScoreUpdateParam, final AppScoreUpdateCallback appScoreUpdateCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iAppScoreUpdateService.appScoreUpdate(reqNo, CommonUtils.signReq(reqNo, gson.toJson(appScoreUpdateParam)), appScoreUpdateParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				BaseBusinessBean<?> baseBusinessBean = handleResponse(response, BaseBusinessBean.class, SHOW_ERROR_TOAST);
				if (baseBusinessBean != null) {
					if (appScoreUpdateCallback != null) {
						appScoreUpdateCallback.doCallback();
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (appScoreUpdateCallback != null) {
					appScoreUpdateCallback.doCallback();
				}
			}

		});
	}

	public interface AppScoreUpdateCallback {

		public void doCallback();

	}

}
