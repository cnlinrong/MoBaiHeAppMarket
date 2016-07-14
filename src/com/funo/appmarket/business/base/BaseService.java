package com.funo.appmarket.business.base;

import com.funo.appmarket.bean.base.BaseBusinessBean;
import com.funo.appmarket.constant.Constants;
import com.funo.appmarket.util.ToastUtils;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;

import android.content.Context;
import android.text.TextUtils;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

	protected final int PAGE_SIZE = 20;
	
	protected final boolean SHOW_ERROR_TOAST = true;
	protected final boolean HIDE_ERROR_TOAST = false;
	
	protected Context mContext;
	
	/**
	 * 请求码：成功
	 */
	protected final String REQUEST_CODE_SUCCESS = "0000";
	
	protected Gson gson = new Gson();
	
	protected Retrofit retrofit;

	protected BaseService(Context context) {
		this.mContext = context;
		
		retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create()).build();
	}

	protected void reportError(boolean showToast, String errorMsg) {
		CrashReport.postCatchedException(new RuntimeException("网络请求错误：" + errorMsg));
		
		if (showToast) {
			ToastUtils.showShortToast(mContext, "网络请求错误，请重试。");
		}
	}
	
	protected <T extends BaseBusinessBean<?>> T handleResponse(Response<ResponseBody> response, Class<T> clz) {
		T t = null;
		try {
			t = gson.fromJson(response.body().string(), clz);
			if (!TextUtils.equals(REQUEST_CODE_SUCCESS, t.getCode())) {
				reportError(SHOW_ERROR_TOAST, t.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();

			reportError(SHOW_ERROR_TOAST, e.getMessage());
		}
		return t;
	}
	
}
