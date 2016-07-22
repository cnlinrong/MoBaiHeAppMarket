package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBigType;
import com.funo.appmarket.bean.AppBigTypeBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IAppBigTypeService;
import com.funo.appmarket.business.define.IAppBigTypeService.AppBigTypeParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppBigTypeService extends BaseService {

	private IAppBigTypeService iAppBigTypeService;

	public AppBigTypeService(Context context) {
		super(context);

		iAppBigTypeService = retrofit.create(IAppBigTypeService.class);
	}

	/**
	 * 查询应用大类信息
	 * 
	 * @param appBigTypeParam
	 * @param appBigTypeCallback
	 */
	public void app_bigType(AppBigTypeParam appBigTypeParam, final AppBigTypeCallback appBigTypeCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iAppBigTypeService.app_bigType(reqNo, CommonUtils.signReq(reqNo, gson.toJson(appBigTypeParam)), appBigTypeParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				AppBigTypeBusinessBean appBigTypeBusinessBean = handleResponse(response, AppBigTypeBusinessBean.class);
				if (appBigTypeBusinessBean != null) {
					if (appBigTypeCallback != null) {
						appBigTypeCallback.doCallback(appBigTypeBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (appBigTypeCallback != null) {
					appBigTypeCallback.doCallback(null);
				}
			}

		});
	}

	public interface AppBigTypeCallback {

		public void doCallback(List<AppBigType> appBigTypes);

	}

}
