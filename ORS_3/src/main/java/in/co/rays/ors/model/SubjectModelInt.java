package in.co.rays.ors.model;

import java.util.List;

import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SubjectModelInt.
 */
public interface SubjectModelInt {

	/**
	 * Add.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Delete.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 */
	public void delete(SubjectDTO dto) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Findbypk.
	 *
	 * @param pk the pk
	 * @return the subject DTO
	 * @throws ApplicationException 
	 */
	public SubjectDTO findbypk(long pk) throws ApplicationException;
	
	/**
	 * Findbyname.
	 *
	 * @param name the name
	 * @return the subject DTO
	 * @throws ApplicationException 
	 */
	public SubjectDTO findbyname(String name) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(SubjectDTO dto , int pageNo , int pageSize) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(SubjectDTO dto) throws ApplicationException;
	
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
	public List list(int pageNo,int pageSize) throws ApplicationException;
}
