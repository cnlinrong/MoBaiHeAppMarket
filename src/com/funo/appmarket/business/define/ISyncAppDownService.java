package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ISyncAppDownService {

	/**
	 * 应用下载次数同步
	 * 
	 * @param reqno
	 * @param sign
	 * @param syncAppDownParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("syncAppDown")
	Call<ResponseBody> syncAppDown(@Query("reqno") String reqno, @Query("sign") String sign, @Body SyncAppDownParam syncAppDownParam);

	public class SyncAppDownParam {

		public String eqNo;// 设备号
		public long appId;// 应用ID

	}

}
