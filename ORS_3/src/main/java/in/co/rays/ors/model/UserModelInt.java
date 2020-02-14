package in.co.rays.ors.model;

import java.util.List;

import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DatabaseException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.exception.RecordNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserModelInt.
 */
public interface UserModelInt {

	
	public Integer nextPK() throws DatabaseException, ApplicationException;
	
	/**
	 * Add.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 * @throws RecordNotFoundException 
	 */
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException;
	
	/**
	 * Delete.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 */
	public void delete(UserDTO dto) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 * @throws RecordNotFoundException 
	 */
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException;
	
	/**
	 * Findbypk.
	 *
	 * @param pk the pk
	 * @return the user DTO
	 * @throws ApplicationException 
	 */
	public UserDTO findbypk(long pk) throws ApplicationException;
	
	/**
	 * Findbylogin.
	 *
	 * @param login
	 * @return the user DTO
	 * @throws ApplicationException 
	 */
	public UserDTO findbylogin(String login) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(UserDTO dto , int pageNo , int pageSize) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(UserDTO dto) throws ApplicationException;
	
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
	public List list (int pageNo , int pageSize) throws ApplicationException;
	
	/**
	 * Register user.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 * @throws RecordNotFoundException 
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException;
	
	/**
	 * Forgetpasword.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 * @throws RecordNotFoundException 
	 */
	public boolean forgetpasword(String login) throws ApplicationException, DuplicateRecordException, RecordNotFoundException;
	
	/**
	 * Change password.
	 *
	 * @param id the id
	 * @param password the password
	 * @param oldPassword the old password
	 * @return true, if successful
	 * @throws ApplicationException 
	 * @throws RecordNotFoundException 
	 * @throws DuplicateRecordException 
	 */
	public boolean changePassword(long id , String password , String oldPassword) throws ApplicationException, RecordNotFoundException, DuplicateRecordException;
	
	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user DTO
	 * @throws ApplicationException 
	 */
	public UserDTO authenticate (String login , String password) throws ApplicationException;
}
