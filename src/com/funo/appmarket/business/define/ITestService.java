package com.funo.appmarket.business.define;

import com.funo.appmarket.bean.LawyerBusinessBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITestService {

	@GET("lawyer/list")
	Call<LawyerBusinessBean> getLawyers();

}
