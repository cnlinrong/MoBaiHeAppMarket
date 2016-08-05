package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.RecAppInfoBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IAppService;
import com.funo.appmarket.business.define.IAppService.RecAppInfoReqParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppService extends BaseService {

	private IAppService iAppService;

	public AppService(Context context) {
		super(context);

		iAppService = retrofit.create(IAppService.class);
	}

	/**
	 * 首页或分类页推荐应用查询
	 */
	public void recAppInfo(final RecAppInfoCallback getLawyersCallback) {
		RecAppInfoReqParam recAppInfoReqParam = new RecAppInfoReqParam("0", PAGE_SIZE, 1);
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iAppService.recAppInfo(reqNo,
				CommonUtils.signReq(reqNo, gson.toJson(recAppInfoReqParam)), recAppInfoReqParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if (getLawyersCallback != null) {
					RecAppInfoBusinessBean recAppInfoBusinessBean = handleResponse(response, RecAppInfoBusinessBean.class, SHOW_ERROR_TOAST);
					if (recAppInfoBusinessBean != null) {
						getLawyersCallback.doCallback(recAppInfoBusinessBean.getResponseData());
					} else {
						getLawyersCallback.doCallback(null);
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (getLawyersCallback != null) {
					getLawyersCallback.doCallback(null);
				}
			}

		});
	}

	public interface RecAppInfoCallback {

		public void doCallback(List<AppBean> appData);

	}

}
