package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.SearchAppByTypeBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ISearchAppByTypeService;
import com.funo.appmarket.business.define.ISearchAppByTypeService.SearchAppByTypeParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAppByTypeService extends BaseService {

	private ISearchAppByTypeService iSearchAppByTypeService;

	public SearchAppByTypeService(Context context) {
		super(context);

		iSearchAppByTypeService = retrofit.create(ISearchAppByTypeService.class);
	}

	/**
	 * 查询相关子类下的应用信息
	 * 
	 * @param searchAppByTypeParam
	 * @param searchAppByTypeCallback
	 */
	public void searchAppByType(SearchAppByTypeParam searchAppByTypeParam, final SearchAppByTypeCallback searchAppByTypeCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iSearchAppByTypeService.searchAppByType(reqNo, CommonUtils.signReq(reqNo, gson.toJson(searchAppByTypeParam)), searchAppByTypeParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				SearchAppByTypeBusinessBean searchAppByTypeBusinessBean = handleResponse(response, SearchAppByTypeBusinessBean.class, SHOW_ERROR_TOAST);
				if (searchAppByTypeBusinessBean != null) {
					if (searchAppByTypeCallback != null) {
						searchAppByTypeCallback.doCallback(searchAppByTypeBusinessBean.getResponseData(), searchAppByTypeBusinessBean.getTotalPage());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (searchAppByTypeCallback != null) {
					searchAppByTypeCallback.doCallback(null, 0);
				}
			}

		});
	}

	public interface SearchAppByTypeCallback {

		public void doCallback(List<AppBean> appBeans, int pageCount);

	}

}
