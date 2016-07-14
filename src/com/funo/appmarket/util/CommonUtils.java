package com.funo.appmarket.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.funo.appmarket.constant.Constants;

import android.graphics.Color;

public class CommonUtils {

	public static int getRandomColor() {
		Random random = new Random();
		int index = random.nextInt(Constants.colors.length);
		return Color.parseColor(Constants.colors[index]);
	}
	
	/**
	 * 生成随机码
	 * 
	 * @return
	 */
	public static String genReqNo() {
		String reqNo = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		reqNo = simpleDateFormat.format(new Date());
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			reqNo += random.nextInt(10);
		}
		return reqNo;
	}
	
	/**
	 * 签名
	 * 
	 * @param reqJson
	 * 
	 * @return
	 */
	public static String signReq(String reqNo, String reqJson) {
		String str = reqNo + Constants.PRIVATE_KEY + reqJson + Constants.PRIVATE_KEY;
		String signedReq = MD5Utils.toMD5(str);
		return signedReq;
	}
	
}
