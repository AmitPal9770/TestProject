package in.co.rays.ors.dto;

import java.util.Date;


/**
 * TimeTable JavaBean encapsulates TimeTable attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */
public class TimeTableDTO extends BaseDTO {

	/**
     * SubjectID of TimeTable
     */
	private long subjectId;

	/**
     * SubjectName of TimeTable
     */
	private String subjectName;

	/**
     * CourseID of TimeTable
     */
	private long courseId;

	/**
     * CourseName of TimeTable
     */
	private String courseName;



	/**
     * Semester Wise of TimeTable
     */
	private String semester;

	/**
     * ExamDate of TimeTable
     */
	private Date examDate;

	/**
     * ExamTime of TimeTable
     */
	private String examTime;
	/**
     * ExamTime of TimeTable
     */

	
	/**
     * Getter and Setter of TimeTable
     */
	
	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

    public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return subjectName;
    }
}
