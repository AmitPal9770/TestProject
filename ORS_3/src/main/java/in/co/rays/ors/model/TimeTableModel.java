package in.co.rays.ors.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.dto.TimeTableDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.JDBCDataSource;

public class TimeTableModel implements TimeTableModelInt{
	private static Logger log = Logger.getLogger(TimeTableModel.class);
	
	/**
	 * Find next PK of TIMETABLE
	 * 
	 * @throws ApplicationException
	 * 
	 * 
	 */

	public Integer nextPk() throws ApplicationException {
		log.debug("Timetable model nextPk method Started ");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_TIMETABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("database Exception ...", e);
			throw new ApplicationException("Exception in NextPk of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable model nextpk method end");
		return pk + 1;
	}

	/**
	 * Add a TIMETABLE
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 * 
	 * 
	 */

	public long add(TimeTableDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("TimeTable model Add method End");
		Connection conn = null;
		long pk = 0;

		CourseModel coumodel= new CourseModel();
		CourseDTO coubean=    coumodel.findbypk(bean.getCourseId());
		String courseName= coubean.getName();
		
		SubjectModel  smodel = new SubjectModel();
		SubjectDTO sbean = smodel.findbypk(bean.getSubjectId());
		String subjectName= sbean.getSubjectName();
		
//		System.out.println("innnnnnnnnnnnnnnnnn adddddddddd method .................>>>>>>");
//		System.out.println("______________________________>>>>>"+bean.getCourseId());
//		System.out.println("______________________________>>>>>"+bean.getSemester());
//		System.out.println("______________________________>>>>>"+bean.getExamDate());
	//	TimeTableModel model = new TimeTableModel();
	
		/*TimeTableDTO bean11 = checkBycds(bean.getCourseId(), bean.getSemester(),  new java.sql.Date(bean.getExamDate().getTime()));
		TimeTableDTO bean12 = checkBycss(bean.getCourseId(), bean.getSubjectId(), bean.getSemester());
		 if(bean11 != null || bean12 != null){ 
			 throw new DuplicateRecordException("TimeTable Already Exsist"); 
			 
		 }*/
	
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getSubjectId());
			pstmt.setString(3, subjectName);
			
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, courseName);
			pstmt.setString(6, bean.getSemester());
		pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			//	pstmt.setDate(8, bean.getExamDate().getTime());
			pstmt.setString(8, bean.getExamTime());
			pstmt.setString(9, bean.getCreatedby());
			pstmt.setString(10, bean.getModifiedby());
			pstmt.setTimestamp(11, bean.getCreateddatetime());
			pstmt.setTimestamp(12, bean.getModifieddatetime());
			pstmt.executeUpdate();
			

			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception ...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception in the Rollback of TIMETABLE Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Add method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable model Add method End");
		return pk;
		
	}

	/**
	 * Delete a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * 
	 */

	public void delete(TimeTableDTO bean) throws ApplicationException {
		log.debug("TIMETABLE Model Delete method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			log.error("database Exception ...", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception in Rollback of Delte Method of TIMETABLE Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TIMETABLE Model Delete method End");
	}

	/**
	 * Update a TIMETABLE
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * 
	 */

	public void update(TimeTableDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("TIMETABLE Model update method Started");
		Connection conn = null;
		CourseModel coumodel= new CourseModel();
		CourseDTO coubean=    coumodel.findbypk(bean.getCourseId());
		String courseName= coubean.getName();
		
		SubjectModel  smodel = new SubjectModel();
		SubjectDTO sbean = smodel.findbypk(bean.getSubjectId());
		String subjectName= sbean.getSubjectName();
	
		
		TimeTableDTO bean11 = checkBycds(bean.getCourseId(), bean.getSemester(),  new java.sql.Date(bean.getExamDate().getTime()));
		TimeTableDTO bean12 = checkBycss(bean.getCourseId(), bean.getSubjectId(), bean.getSemester());
		 if(bean11 != null || bean12 != null){ 
			 throw new DuplicateRecordException("TimeTable Already Exsist"); 
			 
		 }
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET SUBJECTID=? , SUBJECTNAME =? ,COURSEID=?,COURSENAME=?, SEMESTER=?,EXAMDATE=?,EXAMTIME=? ,CREATEDBY=? , MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDATETIME=? WHERE ID=?");

			pstmt.setLong(1, bean.getSubjectId());
			pstmt.setString(2, subjectName);
			
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, courseName);
			pstmt.setString(5, bean.getSemester());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getExamTime());
			pstmt.setString(8, bean.getCreatedby());
			pstmt.setString(9, bean.getModifiedby());
			pstmt.setTimestamp(10, bean.getCreateddatetime());
			pstmt.setTimestamp(11, bean.getModifieddatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Update Method of TimeTable Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in update Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model Update method End");
	}

	/**
	 * Find User by TimeTable Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 * 
	 */

	public TimeTableDTO findByName(String name) throws ApplicationException {
		log.debug("TimeTable Model findByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE SubjectName = ?");
		Connection conn = null;
		TimeTableDTO bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();

				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
		//		bean.setDescription(rs.getString(4));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByName Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model findByName method End");
		return bean;
	}

	/**
	 * Find User by TimeTable PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */
	public TimeTableDTO findbypk(long pk) throws ApplicationException {
		log.debug("TimeTable Model findBypk method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		Connection conn = null;
		TimeTableDTO bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();

				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("TimeTable Model findByPk method End");
		return bean;
	}

	/**
	 * Search TimeTable
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 * 
	 */

	public List search(TimeTableDTO bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search TimeTable with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 * 
	 * 
	 */

	public List search(TimeTableDTO bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("TimeTable Model search method Started");

		Connection conn = null;
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");
				
		if (bean != null) {
	/*		if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
	*/		
			if(bean.getCourseId() > 0){
				sql.append(" AND CourseID = " + bean.getCourseId()); 
			}
			if(bean.getSubjectId() > 0){
				sql.append(" AND SubjectID = " + bean.getSubjectId()); 
			}
			if (bean.getExamDate() !=null && bean.getExamDate().getTime() >0 ){
				
//				System.out.println("===============...>>>>"+bean.getExamDate());
				Date d = new Date(bean.getExamDate().getTime());
				sql.append(" AND ExamDate = '" + d + "'");
//				System.out.println("sql statement ==="+d);
			}
			
		/*	if (bean.getCourseName() !=null && bean.getCourseName().length() >0 ) {
				sql.append(" AND CourseName like '" + bean.getCourseName() + "%'");
			}
			
			if (bean.getSubjectName() !=null && bean.getSubjectName().length() >0 ) {
				sql.append(" AND SubjectName like '" + bean.getSubjectName() + "%'");
			}
		*/	
		}

		// Page Size is greater then Zero then apply pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println("sql queryyyyyyyyyyyyy "+sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();

				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in search Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model search method End");
		return list;
	}

	/**
	 * Get List of TimeTable
	 * 
	 * @return list : List of TimeTable
	 * @throws ApplicationException
	 * 
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of TimeTable with pagination
	 * 
	 * @return list : List of TimeTable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 * 
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("TimeTable Model list method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE ");

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println("-------00----"+sql);
		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				TimeTableDTO bean = new TimeTableDTO();

				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
			//	bean.setDescription(rs.getString(4));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Timetable Model list method End");
		return list;
	}
	
    public  TimeTableDTO checkBycss(long CourseId , long SubjectId , String semester) throws ApplicationException
    {
    	System.out.println("in from css.........................<<<<<<<<<<<>>>> ");
    	Connection conn = null ; 
    	TimeTableDTO bean=null;
  //  	java.util.Date ExamDAte,String ExamTime
    	StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE CourseID=? AND SubjectID=? AND Semester=?");
    	
    	try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setSubjectName(rs.getString(3));
//				bean.setDescription(rs.getString(4));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
			}rs.close();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Timetable Model list method End");
		System.out.println("out from css.........................<<<<<<<<<<<>>>> ");
		return bean;
    }

        public TimeTableDTO checkBycds(long CourseId, String Semester ,java.util.Date ExamDate) throws ApplicationException
    {
        	System.out.println("in from cds.........................<<<<<<<<<<<>>>> ");
        	StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE CourseID=? AND Semester=? AND ExamDate=?");
        	
        	Connection conn = null;
    	TimeTableDTO bean=null;
//    	Date ExDate = new Date(ExamDAte.getTime());
    	
    	
    	try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setString(2, Semester);
			ps.setDate(3, (java.sql.Date)ExamDate);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setSubjectName(rs.getString(3));
//				bean.setDescription(rs.getString(4));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
			
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreateddatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
			}
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Timetable Model list method End");
	System.out.println("out from cds.........................<<<<<<<<<<<>>>> ");
		return bean;
    }

		

		
    
   /* public static TimeTableBean checkBysemester(long CourseId,long SubjectId,String semester,java.util.Date ExamDAte)
    {
    	
    	TimeTableBean bean=null;
    	
    	Date ExDate = new Date(ExamDAte.getTime());
    	
    	StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND"
    			+ " SEMISTER=? AND EXAM_DATE=?");
    	
    	try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			ps.setDate(4, ExDate);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setSubjectName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
    }
    
    public static TimeTableBean checkByCourseName(long CourseId,java.util.Date ExamDate)
    {
    	Connection conn = null;
    	TimeTableBean bean=null;
    	
    	Date Exdate = new Date(ExamDate.getTime());
    	
    	StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? "
    			+ "AND EXAM_DATE=?");
    	
    	try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setDate(2, Exdate);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setSubjectName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
    }
    


*/	
}
