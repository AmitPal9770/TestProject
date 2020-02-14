package in.co.rays.ors.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of FacultyModel
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */
public class FacultyModelHibImpl implements FacultyModelInt {

	/**
	 * Add a Faculty
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 * 
	 */

	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {

		FacultyDTO duplicate = findByEmail(dto.getEmailId());
		if (duplicate != null) {
			throw new DuplicateRecordException("Faculty Already Exsist");
		}

		long pk = 0;
		try {

			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add of FacultyModelHibImpl");
		}
		return pk;
	}

	/**
	 * Delete a Faculty
	 * 
	 * @param bean
	 * 
	 */

	public void delete(FacultyDTO dto) throws ApplicationException {

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete of FacultyModelHibImpl");

		}
	}

	/**
	 * Update a Faculty
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {

		FacultyDTO duplicate = findByEmail(dto.getEmailId());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("Faculty Already Exsist");
		}

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Update of FacultyModelHibImpl");

		}

	}

	/**
	 * Find Faculty by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public FacultyDTO findByEmail(String email) throws ApplicationException {
		FacultyDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(FacultyDTO.class);
			crit.add(Restrictions.eq("emailId", email));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (FacultyDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in find BY name in FacultyModelHIbimpl");
		}

		return dto;
	}

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public FacultyDTO findbypk(long pk) throws ApplicationException {

		FacultyDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(FacultyDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (FacultyDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in find by PK in FacultyModelHIbimpl");
		}
		return dto;
	}

	/**
	 * Search Faculty
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(FacultyDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Facultys
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {

		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(FacultyDTO.class);
			if (dto != null) {

				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
					crit.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
				}
				if (dto.getLastName() != null && dto.getLastName().length() > 0) {
					crit.add(Restrictions.like("lastName", dto.getLastName() + "%"));
				}
				if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
					crit.add(Restrictions.like("emailId", dto.getEmailId() + "%"));
				}
			}
			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}

			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of FacultyModelHibImpl");
		}

		return list;
	}

	/**
	 * Get List of Faculty
	 * 
	 * @return list : List of Faculty
	 * 
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Facultys
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 */

	public List list(int pageNo, int PageSize) throws ApplicationException {
		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(FacultyDTO.class);

			if (PageSize > 0) {
				crit.setFirstResult((pageNo - 1) * PageSize);
				crit.setMaxResults(PageSize);
			}

			list = crit.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in List of FacultyMOdelHIbImpl");
		}

		return list;
	}

}
