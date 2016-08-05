package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.AppBean;
import com.funo.appmarket.bean.InstalledAppInfoBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.IInstalledAppInfoService;
import com.funo.appmarket.business.define.IInstalledAppInfoService.InstalledAppInfoParam;
import com.funo.appmarket.util.CommonUtils;

import android.content.Context;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstalledAppInfoService extends BaseService {

	private IInstalledAppInfoService iInstalledAppInfoService;

	public InstalledAppInfoService(Context context) {
		super(context);

		iInstalledAppInfoService = retrofit.create(IInstalledAppInfoService.class);
	}

	/**
	 * 根据应用ID查询已安装应用信息
	 * 
	 * @param installedAppInfoParam
	 * @param installedAppInfoCallback
	 */
	public void installedAppInfo(InstalledAppInfoParam installedAppInfoParam, final InstalledAppInfoCallback installedAppInfoCallback) {
		String reqNo = CommonUtils.genReqNo();
		Call<ResponseBody> call = iInstalledAppInfoService.installedAppInfo(reqNo, CommonUtils.signReq(reqNo, gson.toJson(installedAppInfoParam)), installedAppInfoParam);
		call.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				InstalledAppInfoBusinessBean installedAppInfoBusinessBean = handleResponse(response, InstalledAppInfoBusinessBean.class, SHOW_ERROR_TOAST);
				if (installedAppInfoBusinessBean != null) {
					if (installedAppInfoCallback != null) {
						installedAppInfoCallback.doCallback(installedAppInfoBusinessBean.getResponseData());
					}
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				reportError(SHOW_ERROR_TOAST, t.getMessage());
				if (installedAppInfoCallback != null) {
					installedAppInfoCallback.doCallback(null);
				}
			}

		});
	}

	public interface InstalledAppInfoCallback {

		public void doCallback(List<AppBean> appBeans);

	}

}
