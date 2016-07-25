package com.funo.appmarket.business.define;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ISearchAppByTypeService {

	/**
	 * 查询相关子类下的应用信息
	 * 
	 * @param reqno
	 * @param sign
	 * @param searchAppByTypeParam
	 * 
	 * @return
	 */
	@Headers({ "Content-type: application/json" })
	@POST("searchAppByType")
	Call<ResponseBody> searchAppByType(@Query("reqno") String reqno, @Query("sign") String sign, @Body SearchAppByTypeParam searchAppByTypeParam);

	public class SearchAppByTypeParam {

		public String smallTypeId;// 子分类id
		public int orderType;// 根据最热或最新排序 0:最热 1:最新
		public int pageSize;// 一页大小
		public int currentPage;// 当前页面
		
	}

}
