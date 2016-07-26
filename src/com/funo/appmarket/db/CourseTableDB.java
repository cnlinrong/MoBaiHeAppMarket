package com.funo.appmarket.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.ex.DbException;

import com.funo.appmarket.app.MyApplication;
import com.funo.appmarket.model.Lessons;
import com.funo.appmarket.util.DateUtils;

public class CourseTableDB {

	public static DbManager getDb() {
		DbManager db = MyApplication.getInstance().getDb();
		return db;
	}

	// 查询一周的课程表
	public static List<Lessons> searchCourse(Long todayTime) {
		List<Lessons> array;
		try {
			array = getDb().selector(Lessons.class).orderBy("weekday").findAll();
		} catch (Exception e) {
			e.printStackTrace();
			array = null;
		}
		return array;
	}

	// 查询当前是否有课，是否可以签到
	public static boolean isCanSignNow(Long nowTime) {
		try {
			String s = DateUtils.LongToDate(nowTime, "HH:mm:ss");
			Date d = DateUtils.StringToDate(s, "HH:mm:ss");
			nowTime = d.getTime();
			nowTime = nowTime + 5 * 60 * 1000l;// 设置为提前5分钟可以签到
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Lessons> array;
		int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		try {
			array = getDb().selector(Lessons.class).where("startTime", "<=", nowTime)
					.and(WhereBuilder.b("endTime", ">=", nowTime)).and(WhereBuilder.b("weekday", "=", weekday))
					.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			array = null;
		}
		if (array != null && array.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 查找uuid
	public static String searchUUid(Long todayTime) {
		List<Lessons> array;
		Long tempTime;
		try {
			String s = DateUtils.LongToDate(todayTime, "HH:mm:ss");
			Date d = DateUtils.StringToDate(s, "HH:mm:ss");
			todayTime = d.getTime();
			tempTime = todayTime;
			todayTime = todayTime + 10 * 60 * 1000l;// 设置为提前10分钟可以签到
		} catch (Exception e) {
			e.printStackTrace();
			tempTime = todayTime;
		}
		int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		try {
			array = getDb().selector(Lessons.class).where("startTime", "<=", todayTime)
					.and(WhereBuilder.b("endTime", ">=", tempTime)).and(WhereBuilder.b("weekday", "=", weekday))
					.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			array = null;
		}
		if (array != null && array.size() > 0) {
			return array.get(0).getUuid();
		} else {
			return null;
		}
	}

	/**
	 * 根据用户Id查询课程
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public static List<Lessons> searchCourseById(String userId) {
		List<Lessons> array = new ArrayList<Lessons>();
		try {
			List<DbModel> dbModels = getDb().selector(Lessons.class).where("userId", "=", userId)
					.orderBy("courseName", false).groupBy("courseName").select("courseName", "courseId").findAll();
			if (dbModels != null) {
				Lessons lesson = null;
				for (DbModel dbModel : dbModels) {
					lesson = new Lessons();
					lesson.setCourseId(dbModel.getLong("courseId"));
					lesson.setCourseName(dbModel.getString("courseName"));
					array.add(lesson);
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return array;
	}

	// 批量插入数据
	public static void beginTransaction(List<Lessons> array) {
		try {
			for (Lessons bean : array) {
				bean.setUserId("");
				getDb().save(bean);
			}
		} catch (DbException e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 清空表内容
	public static void clearTable() {
		try {
			getDb().dropTable(Lessons.class);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

}
