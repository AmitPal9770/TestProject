package in.co.rays.ors.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of CollegeModel
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

public class CollegeModelHibImpl implements CollegeModelInt {
	/**
	 * Add a College
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 * 
	 */

	public long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		CollegeDTO duplicate = findbyname(dto.getName());
		if (duplicate != null) {
			throw new DuplicateRecordException("College Already Exsist");
		}

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in add of CollegeModelHibimpl");
		}
		return pk;
	}
	/**
	 * Update a College
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * 
	 */

	public void update(CollegeDTO dto) throws ApplicationException, DuplicateRecordException {

		CollegeDTO duplicate = findbyname(dto.getName());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("College Already Exsist");
		}

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.update(dto);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Update of CollegeModelHibimpl");
		}

	}
	/**
	 * Delete a College
	 * 
	 * @param bean
	 * 
	 */

	public void delete(CollegeDTO dto) throws ApplicationException {

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Delete of CollegeModelHibImpl");
		}
	}
	/**
	 * Find College by Name
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 */

	public CollegeDTO findbyname(String name) throws ApplicationException {

		CollegeDTO dto = null;

		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CollegeDTO.class);
			crit.add(Restrictions.eq("name", name));
			List list = crit.list();

			/*
			 * Iterator<CollegeDTO> it = list.iterator(); while (it.hasNext()) {
			 * dto = (CollegeDTO) it.next(); }
			 */

			if (list.size() == 1) {
				dto = (CollegeDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in findbyname in  CollegeModelHibimpl");
		}

		return dto;
	}
	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public CollegeDTO findbypk(long pk) throws ApplicationException {
		CollegeDTO dto = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CollegeDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();
			if (list.size() == 1) {
				dto = (CollegeDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in findbypk of collegeModelHibimpl");

		}
		return dto;
	}
	/**
	 * Search College with pagination
	 * 
	 * @return list : List of Colleges
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(CollegeDTO dto, int pageNo, int pageSize) throws ApplicationException {

		List list = null;
		try {

			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(CollegeDTO.class);

			if (dto != null) {

				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					crit.add(Restrictions.like("name", dto.getName() + "%"));
				}
				if (dto.getCity() != null && dto.getCity().length() > 0) {
					crit.add(Restrictions.like("city", dto.getCity() + "%"));
				}
			}
			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}

			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in findbypk of collegeModelHibimpl");
		}
		return list;
	}
	/**
	 * Search College
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */


	public List search(CollegeDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}
	/**
	 * Get List of College
	 * 
	 * @return list : List of College
	 * 
	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}


	/**
	 * Get List of College with pagination
	 * 
	 * @return list : List of Colleges
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
			Criteria crit = session.createCriteria(CollegeDTO.class);

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in List of the Role Model");
		}
		return list;
	}

}
