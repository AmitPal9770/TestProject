package in.co.rays.ors.model;

import java.util.List;

import org.hibernate.usertype.CompositeUserType;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

/**
 * Data Access Object of Course
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public interface CourseModelInt {
	/**
	 * Add a Course
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Course already exists
	 */
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Update a Course
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 */
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public void delete(CourseDTO dto) throws ApplicationException;

	/**
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public CourseDTO findbyname(String name) throws ApplicationException;

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CourseDTO findbypk(long pk) throws ApplicationException;

	/**
	 * Search Course with pagination
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search Course
	 * 
	 * @return list : List of Course
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(CourseDTO dto) throws ApplicationException;

	/**
	 * Gets List of Course
	 * 
	 * @return list : List of Course
	 * @throws ApplicationException 
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException;

	/**
	 * get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException;

}
