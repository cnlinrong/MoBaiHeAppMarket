package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.AppScoreUpdateBusinessBean;
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
				AppScoreUpdateBusinessBean appScoreUpdateBusinessBean = handleResponse(response, AppScoreUpdateBusinessBean.class, SHOW_ERROR_TOAST);
				if (appScoreUpdateBusinessBean != null) {
					if (appScoreUpdateCallback != null) {
						appScoreUpdateCallback.doCallback(appScoreUpdateBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (appScoreUpdateCallback != null) {
					appScoreUpdateCallback.doCallback(null);
				}
			}

		});
	}

	public interface AppScoreUpdateCallback {

		public void doCallback(List<AppBean> appBeans);

	}

}
