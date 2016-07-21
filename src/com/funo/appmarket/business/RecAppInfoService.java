package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.RecAppInfoBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IRecAppInfoService;
import com.funo.appmarket.business.define.IRecAppInfoService.RecAppInfoReqParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecAppInfoService extends BaseService {

	private IRecAppInfoService iRecAppInfoService;

	public RecAppInfoService(Context context) {
		super(context);

		iRecAppInfoService = retrofit.create(IRecAppInfoService.class);
	}

	/**
	 * 首页或分类页推荐应用查询
	 */
	public void recAppInfo(final RecAppInfoCallback getLawyersCallback) {
		RecAppInfoReqParam recAppInfoReqParam = new RecAppInfoReqParam("0", PAGE_SIZE, 1);
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iRecAppInfoService.recAppInfo(reqNo, CommonUtils.signReq(reqNo, gson.toJson(recAppInfoReqParam)), recAppInfoReqParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if (getLawyersCallback != null) {
					RecAppInfoBusinessBean recAppInfoBusinessBean = handleResponse(response, RecAppInfoBusinessBean.class);
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
