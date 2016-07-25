package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAppScoreUpdateService {

	/**
	 * 应用评分接口
	 * 
	 * @param reqno
	 * @param sign
	 * @param appScoreUpdateParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("appScoreUpdate")
	Call<ResponseBody> appScoreUpdate(@Query("reqno") String reqno, @Query("sign") String sign, @Body AppScoreUpdateParam appScoreUpdateParam);

	public class AppScoreUpdateParam {

		public long appId;// 应用ID
		public String eqId;// 设备流水号
		public int score;// 评分
		
	}

}
