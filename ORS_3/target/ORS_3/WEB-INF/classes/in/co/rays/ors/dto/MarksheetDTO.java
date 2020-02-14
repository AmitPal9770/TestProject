package in.co.rays.ors.dto;

/**
 * Marksheet JavaBean encapsulates Marksheet attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */

public class MarksheetDTO extends BaseDTO {

    /**
     * Rollno of Student
     */
    private String rollNo;
    /**
     * ID of Student
     */
    private long studentId;
    /**
     * Name of Student
     */
    private String studentname;
    /**
     * Physics marks of Student
     */
    private Integer physics;
    /**
     * Chemistry marks of Student
     */
    private Integer chemistry;
    /**
     * Mathematics marks of Student
     */
    private Integer maths;

    /**
     * accessor
     */

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }


    public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getMaths() {
        return maths;
    }

    public void setMaths(Integer maths) {
        this.maths = maths;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    
    public String getKey() {
        return id + "";
    }

    
    public String getValue() {
        return rollNo;
    }

}

