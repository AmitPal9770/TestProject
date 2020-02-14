package in.co.rays.ors.dto;

import java.util.Date;

/**
 * Faculty JavaBean encapsulates Faculty attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */
public class FacultyDTO extends BaseDTO {

    /**
     * First Name of Faculty
     */
	private String firstName;

    /**
     * Last Name of Faculty
     */
	private String lastName;

    /**
     * Gender of Faculty
     */
	private String gender;

	/**
     * Date of Joining of Faculty
     */

	private Date dateofjoining;

    /**
     * Qualification of Faculty
     */
	private String qualification;

	/**
     * Email ID of Faculty
     */
	private String emailId;

    /**
     * Mobile No of Faculty
     */
	private String mobileno;

    /**
     * CollegeID of Faculty
     */
	private long collegeid;

    /**
     * CourseID of Faculty
     */
	private long courseId;


    /**
     * SubjectID of Faculty
     */
	private long subjectId;



    /**
     * Getter and Setter of Faculty
     */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Date getDateofjoining() {
		return dateofjoining;
	}

	public void setDateofjoining(Date dateofjoining) {
		this.dateofjoining = dateofjoining;
	}


	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public long getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(long collegeid) {
		this.collegeid = collegeid;
	}



	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}



	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}



    public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return firstName +""+ lastName;
    }
}
