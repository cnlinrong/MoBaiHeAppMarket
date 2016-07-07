package com.funo.appmarket.util;

import java.util.Random;

import com.funo.appmarket.constant.Constants;

import android.graphics.Color;

public class CommonUtils {

	public static int getRandomColor() {
		Random random = new Random();
		int index = random.nextInt(Constants.colors.length);
		return Color.parseColor(Constants.colors[index]);
	}
	
}
