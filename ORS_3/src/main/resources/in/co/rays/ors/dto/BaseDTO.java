package in.co.rays.ors.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Parent class of all Beans in application. It contains generic attributes.
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 * 
 */

public abstract class BaseDTO implements Serializable, DropdownList, Comparable<BaseDTO> {

	/**
	 * Non Business primary key
	 */
	protected long id;

	/**
	 * Contains USER ID who created this database record
	 */
	protected String createdby;

	/**
	 * Contains USER ID who modified this database record
	 */
	protected String modifiedby;

	/**
	 * Contains Created Timestamp of database record
	 */
	protected Timestamp createddatetime;

	/**
	 * Contains Modified Timestamp of database record
	 */
	protected Timestamp modifieddatetime;

	/**
	 * accessor
	 */



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Timestamp getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(Timestamp createddatetime) {
		this.createddatetime = createddatetime;
	}

	public Timestamp getModifieddatetime() {
		return modifieddatetime;
	}

	public void setModifieddatetime(Timestamp modifieddatetime) {
		this.modifieddatetime = modifieddatetime;
	}
	public int compareTo(BaseDTO next) {
		return getValue().compareTo(next.getValue());
	}


}
