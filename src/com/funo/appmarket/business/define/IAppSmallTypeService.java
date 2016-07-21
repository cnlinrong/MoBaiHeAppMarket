package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAppSmallTypeService {

	/**
	 * 查询应用子类信息
	 * 
	 * @param reqno
	 * @param sign
	 * @param appSmallTypeParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("app_smallType")
	Call<ResponseBody> app_smallType(@Query("reqno") String reqno, @Query("sign") String sign, @Body AppSmallTypeParam appSmallTypeParam);

	public class AppSmallTypeParam {

		String smallTypeValue;// smallTypeValue为空则返回所有数据

		public AppSmallTypeParam(String smallTypeValue) {
			this.smallTypeValue = smallTypeValue;
		}

	}

}
