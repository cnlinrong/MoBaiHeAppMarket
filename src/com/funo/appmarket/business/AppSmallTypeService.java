package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppSmallType;
import com.funo.appmarket.bean.AppSmallTypeBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IAppSmallTypeService;
import com.funo.appmarket.business.define.IAppSmallTypeService.AppSmallTypeParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppSmallTypeService extends BaseService {

	private IAppSmallTypeService iAppSmallTypeService;

	public AppSmallTypeService(Context context) {
		super(context);

		iAppSmallTypeService = retrofit.create(IAppSmallTypeService.class);
	}

	/**
	 * 查询应用子类信息
	 * 
	 * @param appSmallTypeParam
	 * @param appSmallTypeCallback
	 */
	public void app_smallType(AppSmallTypeParam appSmallTypeParam, final AppSmallTypeCallback appSmallTypeCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iAppSmallTypeService.app_smallType(reqNo, CommonUtils.signReq(reqNo, gson.toJson(appSmallTypeParam)), appSmallTypeParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				AppSmallTypeBusinessBean appSmallTypeBusinessBean = handleResponse(response, AppSmallTypeBusinessBean.class);
				if (appSmallTypeBusinessBean != null) {
					if (appSmallTypeCallback != null) {
						appSmallTypeCallback.doCallback(appSmallTypeBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (appSmallTypeCallback != null) {
					appSmallTypeCallback.doCallback(null);
				}
			}

		});
	}

	public interface AppSmallTypeCallback {

		public void doCallback(List<AppSmallType> appSmallTypes);

	}

}
