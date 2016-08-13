package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRecAppInfoService {

	/**
	 * 首页或分类页推荐应用查询
	 * 
	 * @param reqno
	 * @param sign
	 * @param recAppInfoReqParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("recAppInfo")
	Call<ResponseBody> recAppInfo(@Query("reqno") String reqno, @Query("sign") String sign, @Body RecAppInfoReqParam recAppInfoReqParam);

	public class RecAppInfoReqParam {

		public int type;// 0：首页推荐 1：分类页推荐
		public String smallTypeId;
		public int pageSize;
		public int currentPage;

	}

}
