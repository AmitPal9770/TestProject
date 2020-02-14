package in.co.rays.ors.dto;

import java.util.Date;

/**
 * Student JavaBean encapsulates Student attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */

public class StudentDTO extends BaseDTO {

    /**
     * First Name of Student
     */
    private String firstName;
    /**
     * Last Name of Student
     */
    private String lastName;
    /**
     * Date of Birth of Student
     */
    /**
     * CollegeId of Student
     */
    private long collegeId;
    /**
     * College name of Student
     */

    private Date dob;
    /**
     * Mobileno of Student
     */
    private String mobileNo;
    /**
     * Email of Student
     */
    private String emailid;

    /**
     * accessor
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }


    
    public String getKey() {
        return id + "";
    }

    
    public String getValue() {
        return firstName + " " + lastName;
    }

}
