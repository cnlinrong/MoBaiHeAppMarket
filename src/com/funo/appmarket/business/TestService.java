package com.funo.appmarket.business;

import java.util.List;

import com.funo.appmarket.bean.LawyerBean;
import com.funo.appmarket.bean.LawyerBusinessBean;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ITestService;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestService extends BaseService {

	private ITestService iTestService;

	public TestService() {
		super();

		iTestService = retrofit.create(ITestService.class);
	}

	/**
	 * 获取律师列表
	 * 
	 * @return
	 */
	public List<LawyerBean> getLawyers(final GetLawyersCallback getLawyersCallback) {
		List<LawyerBean> lawyers = null;
		Call<LawyerBusinessBean> call = iTestService.getLawyers();
		call.enqueue(new Callback<LawyerBusinessBean>() {

			@Override
			public void onResponse(Call<LawyerBusinessBean> call, Response<LawyerBusinessBean> response) {
				Log.e("TestService-getLawyers", response.body().getResultdesc());
				if (getLawyersCallback != null) {
					getLawyersCallback.doCallback(response.body().getData().getLstdata());
				}
			}

			@Override
			public void onFailure(Call<LawyerBusinessBean> call, Throwable t) {
				Log.e("TestService-getLawyers", t.getMessage());
				if (getLawyersCallback != null) {
					getLawyersCallback.doCallback(null);
				}
			}

		});
		return lawyers;
	}

	public interface GetLawyersCallback {

		public void doCallback(List<LawyerBean> lawyers);

	}

}
