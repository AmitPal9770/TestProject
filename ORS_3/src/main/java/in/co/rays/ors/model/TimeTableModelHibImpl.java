package in.co.rays.ors.model;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.TimeTableDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of TimeTableModel
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */

public class TimeTableModelHibImpl implements TimeTableModelInt {
	/**
	 * Add a TimeTable
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 * 
	 */
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {

		long pk = 0;

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in Add of TimeTableModelHibImpl");
		}

		return pk;
	}

	/**
	 * Delete a TimeTable
	 * 
	 * @param bean
	 * 
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete of TimeTableModelHibImpl");
		}
	}

	/**
	 * Update a TimeTable
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */

	public void update(TimeTableDTO dto) throws ApplicationException {

		Transaction tx = null;
		Session session = HibDataSource.getSession();
		try {

			tx = session.beginTransaction();

			session.update(dto);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Update of TimeTableModelHibImpl");
		} finally {
			session.close();
		}
	}

	/**
	 * Find TimeTable by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public TimeTableDTO findbypk(long pk) throws ApplicationException {
		TimeTableDTO dto = null;

		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(TimeTableDTO.class);

			crit.add(Restrictions.eq("id", pk));

			List list = crit.list();

			if (list.size() == 1) {
				dto = (TimeTableDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}
		return dto;
	}

	/**
	 * Search TimeTable with pagination
	 * 
	 * @return list : List of TimeTables
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(TimeTableDTO.class);

			if (dto != null) {
				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getSubjectId() > 0) {
					crit.add(Restrictions.eq("subjectId", dto.getSubjectId()));
				}
				if (dto.getCourseId() > 0) {
					crit.add(Restrictions.eq("courseId", dto.getCourseId()));
				}
				if (dto.getExamDate() != null) {
					crit.add(Restrictions.eq("examDate", dto.getExamDate()));
				}
			}

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}

		return list;
	}

	/**
	 * Search TimeTable
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(TimeTableDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}

	/**
	 * Get List of TimeTable
	 * 
	 * @return list : List of TimeTable
	 * 
	 */

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	/**
	 * Get List of TimeTable with pagination
	 * 
	 * @return list : List of TimeTables
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(TimeTableDTO.class);

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}

		return list;
	}

	/**
	 * Check by course subject semester
	 * 
	 * @param course
	 *            subject semester : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public TimeTableDTO checkBycss(long CourseId, long SubjectId, String Semester) throws ApplicationException {

		TimeTableDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(TimeTableDTO.class);
			crit.add(Restrictions.eq("courseId", CourseId));
			crit.add(Restrictions.eq("subjectId", SubjectId));
			crit.add(Restrictions.eq("semester", Semester));

			/*
			 * Query q = session.
			 * createQuery("from st_timetable where CourseId=? and SubjectId=? and semester=? "
			 * ); q.setLong(0, CourseId); q.setLong(1, SubjectId); q.setString(2
			 * ,semester);
			 */
			List list = crit.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (TimeTableDTO) it.next();
			}
			/*
			 * if (list.size() == 1) { dto = (TimeTableDTO) list.get(0); }
			 */
		} catch (HibernateException e) {
			e.printStackTrace();
			/*
			 * throw new
			 * ApplicationException("Exception in CHECKBYCSS of SubjectModelHIbIMpl"
			 * );
			 */ }
		return dto;

	}

	/**
	 * check by Course date semester
	 * 
	 * @param course
	 *            Date Semester : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public TimeTableDTO checkBycds(long CourseId, String Semester, java.util.Date ExamDate)
			throws ApplicationException {
		TimeTableDTO dto = null;
		try {
			Session session = HibDataSource.getSession();

			Criteria crit = session.createCriteria(TimeTableDTO.class);
			crit.add(Restrictions.eq("courseId", CourseId));
			crit.add(Restrictions.eq("semester", Semester));
			crit.add(Restrictions.eq("examDate", ExamDate));

			List list = crit.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (TimeTableDTO) it.next();
			}
			/*
			 * if (list.size() == 1) { dto = (TimeTableDTO) list.get(0); }
			 */
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in CHECKBYCSS of SubjectModelHIbIMpl");
		}
		return dto;
	}

}
