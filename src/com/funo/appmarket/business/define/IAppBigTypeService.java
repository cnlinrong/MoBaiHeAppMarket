package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAppBigTypeService {

	/**
	 * 查询应用大类信息
	 * 
	 * @param reqno
	 * @param sign
	 * @param appBigTypeParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("app_bigType")
	Call<ResponseBody> app_bigType(@Query("reqno") String reqno, @Query("sign") String sign, @Body AppBigTypeParam appBigTypeParam);

	public class AppBigTypeParam {

		String bigTypeValue;// bigTypeValue为空则返回所有数据

		public AppBigTypeParam(String bigTypeValue) {
			this.bigTypeValue = bigTypeValue;
		}

	}

}
