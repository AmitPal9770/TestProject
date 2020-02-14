package in.co.rays.ors.model;

import java.util.List;
import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

/**
 * The Interface FacultyModelInt.
 *
 * @author SunilOS
 */
public interface FacultyModelInt {

	/**
	 * Add
	 *
	 * @param dto
	 *           
	 * @return the long
	 * @throws ApplicationException
	 *             the application exception
	 * @throws DuplicateRecordException 
	 */
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete
	 *
	 * @param dto
	 * @throws ApplicationException 
	 *         
	 */
	public void delete(FacultyDTO dto) throws ApplicationException;

	/**
	 * Update.
	 *
	 * @param dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 *           
	 */
	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Findbyname.
	 *
	 * @param name
	 *           
	 * @return the faculty DTO
	 * @throws ApplicationException 
	 */
	public FacultyDTO findByEmail(String name) throws ApplicationException;

	/**
	 * Findbypk.
	 *
	 * @param pk
	 *            
	 * @return the faculty DTO
	 * @throws ApplicationException 
	 */
	public FacultyDTO findbypk(long pk) throws ApplicationException;

	/**
	 * Search.
	 *
	 * @param dto
	 *           
	 * @param pageNo
	 *            
	 * @param pageSize
	 *            
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search.
	 *
	 * @param dto
	 *           
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(FacultyDTO dto) throws ApplicationException;

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
	 * @param pageNo
	 *            
	 * @param PageSize
	 *           
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List list(int pageNo, int PageSize) throws ApplicationException;
}
