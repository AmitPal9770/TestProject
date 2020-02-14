package in.co.rays.ors.model;

import java.util.List;

import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

/**
 * The Interface StudentModelInt.
 */
public interface StudentModelInt {

	/**
	 * Add.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Delete.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 */
	public void delete(StudentDTO dto) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public void update(StudentDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Findbyname.
	 *
	 * @param name the name
	 * @return the student DTO
	 * @throws ApplicationException 
	 */
	public StudentDTO findByEmailId(String name) throws ApplicationException;
	
	/**
	 * Findbypk.
	 *
	 * @param pk the pk
	 * @return the student DTO
	 * @throws ApplicationException 
	 */
	public StudentDTO findByPK(long pk) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(StudentDTO dto) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(StudentDTO dto , int pageNo, int pageSize) throws ApplicationException;
	
	/**
	 * List.
	 *
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List list() throws ApplicationException;
	
	/**
	 * List.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List list(int pageNo , int pageSize) throws ApplicationException;
}
