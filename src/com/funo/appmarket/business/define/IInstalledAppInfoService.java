package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IInstalledAppInfoService {

	/**
	 * 根据应用ID查询已安装应用信息
	 * 
	 * @param reqno
	 * @param sign
	 * @param installedAppInfoParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("installedAppInfo")
	Call<ResponseBody> installedAppInfo(@Query("reqno") String reqno, @Query("sign") String sign, @Body InstalledAppInfoParam installedAppInfoParam);

	public class InstalledAppInfoParam {

		public String appId;// 应用ID,多个之间用逗号分隔
		public int pageSize;// 返回数据条数
		public int currentPage;// 当前页面
		
	}

}
