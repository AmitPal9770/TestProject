package in.co.rays.ors.model;

import java.sql.Date;
import java.util.List;

import in.co.rays.ors.dto.TimeTableDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;

// TODO: Auto-generated Javadoc
/**
 * The Interface TimeTableModelInt.
 */
public interface TimeTableModelInt {

	/**
	 * Add.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException;
	
	/**
	 * Delete.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @throws ApplicationException 
	 * @throws DuplicateRecordException 
	 */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException;
		
	/**
	 * Findbypk.
	 *
	 * @param pk the pk
	 * @return the time table DTO
	 * @throws ApplicationException 
	 */
	public TimeTableDTO findbypk(long pk) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search(TimeTableDTO dto , int pageNo , int pageSize) throws ApplicationException;
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 * @throws ApplicationException 
	 */
	public List search (TimeTableDTO dto) throws ApplicationException;
	
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
	
	public  TimeTableDTO checkBycss(long CourseId , long SubjectId , String semester) throws ApplicationException;
    
    public TimeTableDTO checkBycds(long CourseId, String Semester ,java.util.Date ExamDate) throws ApplicationException;
    

}
