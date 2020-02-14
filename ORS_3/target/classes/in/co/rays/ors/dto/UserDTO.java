package in.co.rays.ors.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User JavaBean encapsulates User attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */

public class UserDTO extends BaseDTO {

    /**
     * Lock Active constant for User
     */
    public static final String ACTIVE = "Active";
    /**
     * Lock Inactive constant for User
     */
    public static final String INACTIVE = "Inactive";
    /**
     * First Name of User
     */
    private String firstName;
    /**
     * Last Name of User
     */
    private String lastName;
    /**
     * Login of User
     */
    private String login;
    /**
     * Password of User
     */
    private String password;
/*    *//**
     * Confirm Password of User
     *//*
    private String confirmPassword;
*/
    /**
     * Date of Birth of User
     */
    private Date dob;
    /**
     * MobielNo of User
     */
    private String mobileNo;
    /**
     * Role of User
     */
    private long roleId;
    /**
     * Gender of User
     */
    private String gender;
  

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


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public long getRoleId() {
		return roleId;
	}


	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	   
    public String getKey() {
        return id + "";
    }

   
	public String getValue() {
        return firstName + " " + lastName;
    }

}
