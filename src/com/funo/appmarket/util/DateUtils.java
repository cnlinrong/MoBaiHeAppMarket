package com.funo.appmarket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

public class DateUtils {

	// public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH：mm：ss";
	public static final String DATE_TIME_PATTERN = "yyyy年MM月dd日 HH:mm";
	public static final String DATE_TIME_PATTERN_TWO = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);
	public static final SimpleDateFormat DATE_TIME_FORMAT_TWO = new SimpleDateFormat(DATE_TIME_PATTERN_TWO);

	public static String getCurrentTime() {
		Date date = new Date();
		Long time = date.getTime();
		return time.toString();
	}

	/*
	 * 将日期转成字符串格式
	 */
	public static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_TIME_FORMAT.format(date);
	}
	/*
	 * 将Long转化成日期格式
	 */
	public static String LongToDate(Long dateStr,String TIME_PATTERN) throws ParseException {
			return new SimpleDateFormat(
					TIME_PATTERN).format(new Date(dateStr));
		
	}
	/*
	 * 将String 转成Date
	 */
	public static Date StringToDate(String dateStr,String TIME_PATTERN) throws ParseException {
		return new SimpleDateFormat(
				TIME_PATTERN).parse(dateStr);
	
    }

	public static String formatDayDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_TIME_FORMAT_TWO.format(date);
	}

	/*
	 * 将字符串转化成日期格式
	 */
	public static Date parseDateTime(String dateStr) {
		try {
			return DATE_TIME_FORMAT.parse(dateStr);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 根据指定的pattern格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * 
	 * @return
	 */
	public static String formatDateWithPattern(Date date, String pattern) {
		if (date == null || pattern == null) {
			return "";
		} else {
			return new SimpleDateFormat(pattern).format(date);
		}
	}

	/**
	 * 根据指定的pattern格式化日期字符串
	 * 
	 * @param dateString
	 * @param pattern
	 * 
	 * @return
	 */
	public static Date parseDateString(String dateString, String pattern) {
		if (TextUtils.isEmpty(dateString) || TextUtils.isEmpty(pattern)) {
			return null;
		} else {
			Date date = null;
			try {
				date = new SimpleDateFormat(pattern).parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	}
	
	/**
	 * 获取指定截止日期的倒计时
	 * 
	 * @param deadline
	 * 
	 * @return
	 */
	public static String countdown(long deadline) {
		String countdownStr = "已过期";
		long currentTime;
		long distTime;
		long days, hours, minutes, seconds;
		currentTime = new Date().getTime();
		if (deadline > currentTime) {
			distTime = deadline - currentTime;
			days = ((distTime / 1000) / (3600 * 24));
			hours = ((distTime / 1000) - days * 86400) / 3600;
			minutes = ((distTime / 1000) - days * 86400 - hours * 3600) / 60;
			seconds = (distTime / 1000) - days * 86400 - hours * 3600 - minutes * 60;
			countdownStr = days + "天" + hours + "时" + minutes + "分";
		}
		return countdownStr;
	}
	
	public static String getWeekName(Date date) {
		String weekName = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			weekName = "周日";
			break;
		case Calendar.MONDAY:
			weekName = "周一";
			break;
		case Calendar.TUESDAY:
			weekName = "周二";
			break;
		case Calendar.WEDNESDAY:
			weekName = "周三";
			break;
		case Calendar.THURSDAY:
			weekName = "周四";
			break;
		case Calendar.FRIDAY:
			weekName = "周五";
			break;
		case Calendar.SATURDAY:
			weekName = "周六";
			break;
		}
		return weekName;
	}
	
}
