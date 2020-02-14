package in.co.rays.ors.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;
/**
 * Hibernate Implementation of CourseModel
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class CourseModelHibImpl implements CourseModelInt {
	/**
	 * Add a Course
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 * 
	 */

	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		
		CourseDTO duplicate = findbyname(dto.getName());
		if(duplicate != null){
			throw new DuplicateRecordException("Course Already Exsist");
		}
		
		try {

			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Add in CourseModelHibImpl");
		}
		return pk;
	}
	/**
	 * Update a Course
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * 
	 */

	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		
		CourseDTO duplicate = findbyname(dto.getName());
//		System.out.println("kjgkjjjjjjjjjjjjjj"+duplicate.getName());
		if(duplicate.getId() != dto.getId() && duplicate != null ){
			
			throw new DuplicateRecordException("Course Already Exsist");
		}
		
		
			Session session = HibDataSource.getSession();
			Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(dto);
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Update of CourseModelHibImpl");
		}

	}
	/**
	 * Delete a Course
	 * 
	 * @param bean
	 * 
	 */

	public void delete(CourseDTO dto) throws ApplicationException {
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete of CourseModelHibImpl");
		}
	}
	/**
	 * Find Course by Name
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 */

	public CourseDTO findbyname(String name) throws ApplicationException {

		CourseDTO dto = null;

		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CourseDTO.class);
			crit.add(Restrictions.eq("name", name));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (CourseDTO) list.get(0);
			}

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Find by Name of CourseModelHibImpl");
		}
		return dto;
	}
	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */


	public CourseDTO findbypk(long pk) throws ApplicationException {

		CourseDTO dto = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CourseDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (CourseDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Find by PK in courseModelHibImpl");
		}
		return dto;
	}
	/**
	 * Search Course with pagination
	 * 
	 * @return list : List of Courses
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {

		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CourseDTO.class);

			if(dto != null){
				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					crit.add(Restrictions.like("name", dto.getName() + "%"));
				}
			}

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Search of CourseModelHibImpl");
		}
		return list;
	}
	/**
	 * Search Course
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */


	public List search(CourseDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Get List of Course
	 * 
	 * @return list : List of Course
	 * 
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}
	/**
	 * Get List of Course with pagination
	 * 
	 * @return list : List of Courses
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		try{
		Session session = HibDataSource.getSession();
		Criteria crit = session.createCriteria(CourseDTO.class);
		
		if (pageSize>0) {
			crit.setFirstResult((pageNo-1)*pageSize);
			crit.setMaxResults(pageSize);
		}
		list = crit.list();
		}catch(HibernateException e ){
			throw new ApplicationException("Exception in List of CourseModelHibImpl");
		}
		return list;
	}

}
