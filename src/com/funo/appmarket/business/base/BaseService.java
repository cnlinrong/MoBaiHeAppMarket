package com.funo.appmarket.business.base;

import com.funo.appmarket.constant.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

	protected Retrofit retrofit;

	protected BaseService() {
		retrofit = new Retrofit.Builder().baseUrl(Constants.TEST_BASE_URL)
				.addConverterFactory(GsonConverterFactory.create()).build();
	}

}
