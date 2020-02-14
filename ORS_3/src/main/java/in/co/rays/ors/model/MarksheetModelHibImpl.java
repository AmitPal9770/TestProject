package in.co.rays.ors.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.MarksheetDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of MarksheetModel
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */
public class MarksheetModelHibImpl implements MarksheetModelInt {

	/**
	 * Add a Marksheet
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 * 
	 */

	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;

		MarksheetDTO duplicate = findbyRollNo(dto.getRollNo());
		if (duplicate != null) {
			throw new DuplicateRecordException("Marksheet Already Exsist");
		}

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		StudentDTO dt1 =model.findByPK(dto.getStudentId());
		String name = dt1.getFirstName();
		
		dto.setStudentname(name);

	
		
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add in MarksheetModelHibImpl");
		}
		return pk;
	}
	/**
	 * Delete a Marksheet
	 * 
	 * @param bean
	 * 
	 */

	public void delete(MarksheetDTO dto) throws ApplicationException {
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Delete in MarksheetModelHibImpl");
		}
	}
	/**
	 * Update a Marksheet
	 * 
	 * @param bean
	 * @throws RecordNotFoundException 
	 * 
	 */

	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {

		MarksheetDTO duplicate = findbyRollNo(dto.getRollNo());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("Marksheet Already Exsist");
		}
		
		
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		StudentDTO dt1 =model.findByPK(dto.getStudentId());
		String name = dt1.getFirstName();
		
		dto.setStudentname(name);

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in update in MarksheetModelHibImpl");
		}

	}

	/**
	 * Find Marksheet by Roll No
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException 
	 * @throws ApplicationException 
	 * 
	 */

	public MarksheetDTO findbyRollNo(String rollno) throws ApplicationException {
		MarksheetDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(MarksheetDTO.class);
			crit.add(Restrictions.eq("rollNo", rollno));
			List list = crit.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				 dto  = (MarksheetDTO) it.next();
				
			}
			
			/*if (list.size() == 1) {
				dto = (MarksheetDTO) list.get(0);
			}*/
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}
		return dto;
	}
	/**
	 * Find Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public MarksheetDTO findbypk(long pk) throws ApplicationException {
		MarksheetDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(MarksheetDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();
			if (list.size() == 1) {
				dto = (MarksheetDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}
		return dto;
	}
	/**
	 * Search Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		System.out.println("in side search hbm.xml");
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(MarksheetDTO.class);
System.out.println("------->>>"+dto);
			if (dto != null) {

				if (dto.getId() > 0) {
					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
					crit.add(Restrictions.like("rollNo", dto.getRollNo() + "%"));
				}
				if(dto.getStudentId()>0){
					crit.add(Restrictions.eq("studentId", dto.getStudentId()));
				}
			}
			if (pageSize > 0) {
				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of MarksheetModelHIbIMpl");
		}
		System.out.println("in side list........");
		return list;
	}
	/**
	 * Search Marksheet
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(MarksheetDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Get List of Marksheet
	 * 
	 * @return list : List of Marksheet
	 * 
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
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
			Criteria crit = session.createCriteria(MarksheetDTO.class);

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

	public List getMeritlist(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			StringBuffer hql = new StringBuffer("from MarksheetDTO where (physics > 33 AND maths >33 AND chemistry > 33) order by (physics+chemistry+maths) desc ");

			pageNo = (pageNo-1) * pageSize;
			Query q = session.createQuery(hql.toString());
			
			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				q.setFirstResult(pageNo);
				q.setMaxResults(pageSize);

			}

			list = q.list();

		} catch (Exception e) {
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

}
