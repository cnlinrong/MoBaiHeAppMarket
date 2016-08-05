package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ISyncEquipmentInfoService {

	/**
	 * 设备信息同步
	 * 
	 * @param reqno
	 * @param sign
	 * @param syncEquipmentInfoParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("syncEquipmentInfo")
	Call<ResponseBody> syncEquipmentInfo(@Query("reqno") String reqno, @Query("sign") String sign, @Body SyncEquipmentInfoParam syncEquipmentInfoParam);

	public class SyncEquipmentInfoParam {

		public String eqNo;// 设备号
		
	}

}
