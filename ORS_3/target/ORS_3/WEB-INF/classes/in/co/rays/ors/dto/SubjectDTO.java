package in.co.rays.ors.dto;


/**
 * Subject JavaBean encapsulates Subject attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */
public class SubjectDTO extends BaseDTO {


	/**
     * Name of Subject
     */
	private String subjectName;

	/**
     * Description of Subject
     */
	private String description;

    /**
     * CourseId of Subject
     */
	private long courseId;

    /**
     * Course Name of Subject
     */


    /**
     * Getter and Setter of Subject
     */

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

	


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

    public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return subjectName;
    }
}
