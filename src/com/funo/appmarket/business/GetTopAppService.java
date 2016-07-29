package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.GetTopAppBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IGetTopAppService;
import com.funo.appmarket.business.define.IGetTopAppService.GetTopAppParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTopAppService extends BaseService {

	private IGetTopAppService iGetTopAppService;

	public GetTopAppService(Context context) {
		super(context);

		iGetTopAppService = retrofit.create(IGetTopAppService.class);
	}

	/**
	 * 获取topN应用
	 * 
	 * @param getTopAppParam
	 * @param getTopAppCallback
	 */
	public void getTopApp(GetTopAppParam getTopAppParam, final GetTopAppCallback getTopAppCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iGetTopAppService.getTopApp(reqNo, CommonUtils.signReq(reqNo, gson.toJson(getTopAppParam)), getTopAppParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				GetTopAppBusinessBean getTopAppBusinessBean = handleResponse(response, GetTopAppBusinessBean.class);
				if (getTopAppBusinessBean != null) {
					if (getTopAppCallback != null) {
						getTopAppCallback.doCallback(getTopAppBusinessBean.getResponseData(), getTopAppBusinessBean.getTotalPage());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (getTopAppCallback != null) {
					getTopAppCallback.doCallback(null, 0);
				}
			}

		});
	}

	public interface GetTopAppCallback {

		public void doCallback(List<AppBean> appBeans, int pageCount);

	}

}
