package in.co.rays.ors.dto;

/**
 * Course JavaBean encapsulates Course attributes
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */
public class CourseDTO extends BaseDTO{

    /**
     * Name of Course
     */
	private String name;

    /**
     * Duration of Course
     */
	
	private String duration;
	
	/**
     * Description of Course
     */
	
	private String description;
	

    /**
     * Getter and Setter of Course
     */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}


    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey() {
        return id+"";
    }

   
    public String getValue() {
        return name;
    }
	
}
