package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IStatusChangeNotifyService {

	/**
	 * 状态变更通知
	 * 
	 * @param reqno
	 * @param sign
	 * @param statusChangeNotifyParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("statusChangeNotify")
	Call<ResponseBody> statusChangeNotify(@Query("reqno") String reqno, @Query("sign") String sign, @Body StatusChangeNotifyParam statusChangeNotifyParam);

	public class StatusChangeNotifyParam {

	}

}
