package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.SearchAppInfoBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ISearchAppInfoService;
import com.funo.appmarket.business.define.ISearchAppInfoService.SearchAppInfoParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAppInfoService extends BaseService {

	private ISearchAppInfoService iSearchAppInfoService;

	public SearchAppInfoService(Context context) {
		super(context);

		iSearchAppInfoService = retrofit.create(ISearchAppInfoService.class);
	}

	/**
	 * 根据应用名称模糊查询应用信息
	 * 
	 * @param searchAppInfoParam
	 * @param searchAppInfoCallback
	 */
	public void searchAppInfo(SearchAppInfoParam searchAppInfoParam, final SearchAppInfoCallback searchAppInfoCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iSearchAppInfoService.searchAppInfo(reqNo, CommonUtils.signReq(reqNo, gson.toJson(searchAppInfoParam)), searchAppInfoParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				SearchAppInfoBusinessBean searchAppInfoBusinessBean = handleResponse(response, SearchAppInfoBusinessBean.class, SHOW_ERROR_TOAST);
				if (searchAppInfoBusinessBean != null) {
					if (searchAppInfoCallback != null) {
						searchAppInfoCallback.doCallback(searchAppInfoBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (searchAppInfoCallback != null) {
					searchAppInfoCallback.doCallback(null);
				}
			}

		});
	}

	public interface SearchAppInfoCallback {

		public void doCallback(List<AppBean> appBeans);

	}

}
