package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGetTopAppService {

	/**
	 * 获取topN应用
	 * 
	 * @param reqno
	 * @param sign
	 * @param getTopAppParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("getTopApp")
	Call<ResponseBody> getTopApp(@Query("reqno") String reqno, @Query("sign") String sign, @Body GetTopAppParam getTopAppParam);

	public class GetTopAppParam {

		public int orderType;// 根据最热或最新排序 0:最热 1:最新
		public int pageSize;// 返回数据条数
		public int currentPage;// 当前页面
		
	}

}
