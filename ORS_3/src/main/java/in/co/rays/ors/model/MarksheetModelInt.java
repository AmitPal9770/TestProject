package in.co.rays.ors.model;

import java.util.List;

import in.co.rays.ors.dto.MarksheetDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * The Interface MarksheetModelInt.
 */
public interface MarksheetModelInt {

	/**
	 * Add.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Delete.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 */
	public void delete(MarksheetDTO dto) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Findbyname.
	 *
	 * @param name the name
	 * @return the marksheet DTO
	 * @throws ApplicationException 
	 */
	public MarksheetDTO findbyRollNo(String rollno) throws ApplicationException;
	
	/**
	 * Findbypk.
	 *
	 * @param pk the pk
	 * @return the marksheet DTO
	 * @throws ApplicationException 
	 */
	public MarksheetDTO findbypk(long pk) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(MarksheetDTO dto , int pageNo , int pageSize) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(MarksheetDTO dto) throws ApplicationException;
	
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
	
	/**
	 * Meritlist.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List getMeritlist(int pageNo , int pageSize) throws ApplicationException;
}
