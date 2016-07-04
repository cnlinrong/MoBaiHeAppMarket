package com.funo.appmarket.business;

import java.io.IOException;
import java.util.List;

import com.funo.appmarket.bean.LawyerBean;
import com.funo.appmarket.bean.LawyerBusinessBean;
import com.funo.appmarket.bean.base.BasePagedData;
import com.funo.appmarket.business.base.BaseService;
import com.funo.appmarket.business.define.ITestService;

import retrofit2.Call;
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
	public List<LawyerBean> getLawyers() {
		List<LawyerBean> lawyers = null;
		Call<LawyerBusinessBean> call = iTestService.getLawyers();
		try {
			Response<LawyerBusinessBean> response = call.execute();
			LawyerBusinessBean lawyerBusinessBean = response.body();
			BasePagedData<LawyerBean> basePagedData = lawyerBusinessBean.getData();
			lawyers = basePagedData.getLstdata();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lawyers;
	}

}
