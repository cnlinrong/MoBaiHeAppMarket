package com.funo.appmarket.model;

import java.io.Serializable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "Lessons")
public class Lessons implements Serializable {

	private static final long serialVersionUID = 2077288469L;

	@Column(name = "id", isId = true, autoGen = true)
	private int id;

	@Column(name = "startTime")
	private long startTime;

	@Column(name = "endTime")
	private long endTime;

	@Column(name = "courseName")
	private String courseName;

	@Column(name = "teacherName")
	private String teacherName;

	@Column(name = "place")
	private String place;

	@Column(name = "weekday")
	private long weekday;

	@Column(name = "lessonId")
	private long lessonId;

	@Column(name = "lesson")
	private long lesson;

	@Column(name = "userId")
	private String userId;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "teacherId")
	private Long teacherId;

	@Column(name = "courseId")
	private Long courseId;

	@Column(name = "subjectName")
	private String subjectName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public long getWeekday() {
		return this.weekday;
	}

	public void setWeekday(long weekday) {
		this.weekday = weekday;
	}

	public long getLessonId() {
		return this.lessonId;
	}

	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}

	public long getLesson() {
		return this.lesson;
	}

	public void setLesson(long lesson) {
		this.lesson = lesson;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
