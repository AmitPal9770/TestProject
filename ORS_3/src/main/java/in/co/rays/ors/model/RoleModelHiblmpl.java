package in.co.rays.ors.model;

import java.util.List;
import java.util.logging.Logger;

import javax.management.relation.Role;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.util.HibDataSource;

/**
 * Hibernate Implementation of RoleModel
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */

public class RoleModelHiblmpl implements RoleModelInt {

	private static Logger log = Logger.getLogger("RoleModelHibImpl.class");

	/**
	 * Add a Role
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 * 
	 */

	public long add(RoleDTO dto) throws ApplicationException, DuplicateRecordException {

		RoleDTO duplicate = findbyname(dto.getName());
		if (duplicate != null) {
			throw new DuplicateRecordException("Role Already Exsist");
		}
		long pk = 0;
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = null;

			tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();

			throw new ApplicationException("Exception in Add of Role model");
		}
		return pk;
	}

	/**
	 * Update a Role
	 * 
	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */

	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException {

		RoleDTO duplicate = findbyname(dto.getName());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("Role Already Exsist");
		}

		Session session = HibDataSource.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Update of Role Model");

		}
	}

	/**
	 * Delete a Role
	 * 
	 * @param bean
	 * 
	 */

	public void delete(RoleDTO dto) throws ApplicationException {

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Delete in role Model");
		}
	}

	/**
	 * Find Role by name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * 
	 */

	public RoleDTO findbyname(String name) throws ApplicationException {

		RoleDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(RoleDTO.class);
			crit.add(Restrictions.eq("name", name));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (RoleDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Find by name of role model....");
		}

		return dto;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * 
	 */

	public RoleDTO findbypk(long pk) throws ApplicationException {
		RoleDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(RoleDTO.class);
			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();
			if (list.size() == 1) {
				dto = (RoleDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in FindbyPK of Rolemodelhibimpl");
		}
		return dto;
	}

	/**
	 * Search Role
	 * 
	 * @param bean
	 *            : Search Parameters
	 * 
	 */

	public List search(RoleDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Search Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * 
	 */

	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException {

		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(RoleDTO.class);
			System.out.println("------->>>" + dto);
			if (dto != null) {
				System.out.println("in conditions");

				if (dto.getId() > 0) {

					crit.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					crit.add(Restrictions.like("name", dto.getName() + "%"));
				}
			}

			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize);
				crit.setFirstResult(pageNo);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of RoleModelHibimpl");
		}
		return list;
	}

	/**
	 * Get List of Role
	 * 
	 * @return list : List of Role
	 * 
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Role with pagination
	 * 
	 * @return list : List of Roles
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
			Criteria crit = session.createCriteria(RoleDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				crit.setFirstResult(pageNo);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in list of RoleModelHIbimpl");

		}
		return list;
	}

}
