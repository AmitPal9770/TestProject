package in.co.rays.ors.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of StudentModel
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */

public class StudentModelHibImpl implements StudentModelInt {
	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 * 
	 */
	public long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;

		System.out.println("----------" + dto.getEmailid());
		StudentDTO duplicate = findByEmailId(dto.getEmailid());

		System.out.println("------------	>>>" + duplicate);
		if (duplicate != null) {
			throw new DuplicateRecordException("Student Already Exsist");
		}

		try {

			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add of StudentModelHibImpl");
		}
		return pk;
	}

	/**
	 * Delete a Student
	 * 
	 * @param bean
	 * 
	 */

	public void delete(StudentDTO dto) throws ApplicationException {
		try {

			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(dto);

			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete of StudentModelHibImpl");
		}
	}

	/**
	 * Update a Student
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */
	public void update(StudentDTO dto) throws ApplicationException, DuplicateRecordException {

		StudentDTO duplicate = findByEmailId(dto.getEmailid());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("Student already Exsist");
		}

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Update of StudentModelHibImpl");
		}
	}

	/**
	 * Find Student by Email
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public StudentDTO findByEmailId(String email) throws ApplicationException {
		StudentDTO dto = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(StudentDTO.class);
			crit.add(Restrictions.eq("emailid", email));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (StudentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add of StudentModelHibImpl");
		}
		return dto;
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public StudentDTO findByPK(long pk) throws ApplicationException {
		StudentDTO dto = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(StudentDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (StudentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Add of StudentModelHibImpl");
		}
		return dto;
	}

	/**
	 * Search Student
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(StudentDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Students
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(StudentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(StudentDTO.class);

			if (dto != null) {

				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
					System.out.println("=====<<" + dto.getFirstName());
					crit.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
				}
				if (dto.getLastName() != null && dto.getLastName().length() > 0) {
					crit.add(Restrictions.like("lastName", dto.getLastName() + "%"));
				}
				if (dto.getEmailid() != null && dto.getEmailid().length() > 0) {
					crit.add(Restrictions.like("emailid", dto.getEmailid() + "%"));
				}
			}
			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add of StudentModelHibImpl");
		}
		return list;
	}

	/**
	 * Get List of Student
	 * 
	 * @return list : List of Student
	 * 
	 */

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	/**
	 * Get List of Student with pagination
	 * 
	 * @return list : List of Students
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
			Criteria crit = session.createCriteria(StudentDTO.class);

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Add of StudentModelHibImpl");
		}
		return list;
	}

}
