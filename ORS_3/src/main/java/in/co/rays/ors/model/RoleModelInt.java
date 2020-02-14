package in.co.rays.ors.model;

import java.util.List;

import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleModelInt.
 */
public interface RoleModelInt {

	/**
	 * Add.
	 *
	 * @param dto
	 *            the dto
	 * @return the long
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 */
	public long add(RoleDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Update.
	 *
	 * @param dto
	 *            the dto @throws
	 * @throws DuplicateRecordException 
	 * 
	 */
	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete.
	 *
	 * @param dto
	 *            the dto
	 * @throws ApplicationException
	 */
	public void delete(RoleDTO dto) throws ApplicationException;

	/**
	 * Findbyname.
	 *
	 * @param name
	 *            the name
	 * @return the role DTO
	 * @throws ApplicationException
	 */
	public RoleDTO findbyname(String name) throws ApplicationException;

	/**
	 * Findbypk.
	 *
	 * @param pk
	 *            the pk
	 * @return the role DTO
	 * @throws ApplicationException
	 */
	public RoleDTO findbypk(long pk) throws ApplicationException;

	/**
	 * Search.
	 *
	 * @param dto
	 *            the dto
	 * @return the list
	 * @throws ApplicationException
	 */
	public List search(RoleDTO dto) throws ApplicationException;

	/**
	 * Search.
	 *
	 * @param dto
	 *            the dto
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws ApplicationException
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException;

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
	 * @param pageno
	 *            the pageno
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws ApplicationException
	 */
	public List list(int pageno, int pageSize) throws ApplicationException;

}
