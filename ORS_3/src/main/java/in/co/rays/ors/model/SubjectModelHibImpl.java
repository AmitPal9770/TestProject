package in.co.rays.ors.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of SubjectModel
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */

public class SubjectModelHibImpl implements SubjectModelInt {

	/**
	 * Add a Subject
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 * 
	 */

	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		SubjectDTO duplicate = findbyname(dto.getSubjectName());
		if (duplicate != null) {
			throw new DuplicateRecordException("Subject Already Exsist");
		}
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add of SubjectModelHibImpl");
		}
		return pk;
	}

	/**
	 * Delete a Subject
	 * 
	 * @param bean
	 * 
	 */

	public void delete(SubjectDTO dto) throws ApplicationException {

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete of SubjectModelHibImpl");
		}
	}

	/**
	 * Update a Subject
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */

	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {

		SubjectDTO duplicate = findbyname(dto.getSubjectName());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("Subject Already Exsist");
		}

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Update of SubjectModelHibImpl");
		}
	}

	/**
	 * Find Subject by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public SubjectDTO findbypk(long pk) throws ApplicationException {
		SubjectDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(SubjectDTO.class);
			crit.add(Restrictions.eq("id", pk));

			List list = crit.list();

			if (list.size() == 1) {
				dto = (SubjectDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in FindBYpk in subjectModelHIbIMpl");
		}
		return dto;
	}

	/**
	 * Find Subject by name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public SubjectDTO findbyname(String name) throws ApplicationException {
		SubjectDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(SubjectDTO.class);

			crit.add(Restrictions.eq("subjectName", name));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (SubjectDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Eception in Find by Name of SubjectModelHibImpl");
		}

		return dto;
	}

	/**
	 * Search Subject with pagination
	 * 
	 * @return list : List of Subjects
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(SubjectDTO.class);

			if (dto != null) {

				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getSubjectName() != null && dto.getSubjectName().length() > 0) {
					crit.add(Restrictions.like("subjectName", dto.getSubjectName() + "%"));
				}
				if (dto.getCourseId() > 0) {
					crit.add(Restrictions.eq("courseId", dto.getCourseId()));
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
	 * Search Subject
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(SubjectDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Get List of Subject
	 * 
	 * @return list : List of Subject
	 * 
	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	/**
	 * Get List of Subject with pagination
	 * 
	 * @return list : List of Subjects
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
			Criteria crit = session.createCriteria(SubjectDTO.class);

			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in List of SubjectModelHibImpl");
		}
		return list;
	}

}
