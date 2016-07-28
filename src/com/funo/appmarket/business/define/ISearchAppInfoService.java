package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ISearchAppInfoService {

	/**
	 * 根据应用名称模糊查询应用信息
	 * 
	 * @param reqno
	 * @param sign
	 * @param searchAppInfoParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("searchAppInfo")
	Call<ResponseBody> searchAppInfo(@Query("reqno") String reqno, @Query("sign") String sign, @Body SearchAppInfoParam searchAppInfoParam);

	public class SearchAppInfoParam {

//		public String appName;// 应用名称，模糊查询
		public String appPy;// 应用拼音首字母
//		public long appId;// 应用ID
		public int pageSize;// 一页大小
		public int currentPage;// 当前页面
		
	}

}
