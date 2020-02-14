package in.co.rays.ors.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DatabaseException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.exception.RecordNotFoundException;
import in.co.rays.ors.util.EmailBuilder;
import in.co.rays.ors.util.EmailMessage;
import in.co.rays.ors.util.EmailUtility;
import in.co.rays.ors.util.HibDataSource;

public class UserModelHibImpl implements UserModelInt {
	
	public Integer nextPK() throws DatabaseException, ApplicationException {

		Session session = null;
		int row;
	try{
		session = HibDataSource.getSession();
		Criteria crit = session.createCriteria(UserDTO.class);
		
		session.beginTransaction();
		crit.setProjection(Projections.rowCount());
		
		session.beginTransaction().commit();
		row = ((Integer)crit.list().get(0)).intValue();
	}catch(HibernateException e){
		e.printStackTrace();
		throw new ApplicationException("Exception : Exception in User Next Pk of Hibernate");
	}finally{
		session.close();
	}	
		
		return row;
	}

	
	
	/**
	 * Add a new user
	 * 
	 * @param dto
	 * @return long pk
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * @throws RecordNotFoundException
	 */	
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;

		UserDTO duplicate = findbylogin(dto.getLogin());
		if (duplicate != null) {
			throw new DuplicateRecordException("User Already Exsist");
		}

		/*
		 * RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		 * RoleDTO roledto = model.findbypk(dto.getId()); String rolename =
		 * roledto.getName();
		 */
		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Add in userModelHibImpl");
		}

		return pk;
	}

	/**
	 * Delete a user
	 * 
	 * @param userDTO
	 * @throws ApplicationException
	 */

	public void delete(UserDTO dto) throws ApplicationException {

		try {
			Session session = HibDataSource.getSession();
			Transaction tx = session.beginTransaction();

			session.delete(dto);

			tx.commit();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in Delete in userModelHibImpl");
		}

	}

	/**
	 * Update a user
	 * 
	 * @param userDTO
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */

	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {

		UserDTO duplicate = findbylogin(dto.getLogin());
		if (duplicate != null && duplicate.getId() != dto.getId()) {
			throw new DuplicateRecordException("User Already Exsist");
		}

		Transaction tx = null;
		Session session = HibDataSource.getSession();

		try {
			tx = session.beginTransaction();

			session.update(dto);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Add in userModelHibImpl");
		} finally {
			session.close();
		}

	}

	/**
	 * Find a user by PK(primary key)
	 * 
	 * @param pk
	 * @return userdto
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */

	public UserDTO findbypk(long pk) throws ApplicationException {
		UserDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(UserDTO.class);

			crit.add(Restrictions.eq("id", pk));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (UserDTO) list.get(0);
			}
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}

		return dto;
	}

	/**
	 * Find a user by LoginId
	 * 
	 * @param login
	 * @return
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */

	public UserDTO findbylogin(String login) throws ApplicationException {

		UserDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(UserDTO.class);

			crit.add(Restrictions.eq("login", login));
			List list = crit.list();

			if (list.size() == 1) {
				dto = (UserDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of SubjectModelHIbIMpl");
		}

		return dto;
	}

	/**
	 * Search a user with pagination
	 * 
	 * @param dto
	 * @param pageNo
	 * @param pageSize
	 * @return: list of user(s)
	 * @throws ApplicationException
	 */

	public List search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(UserDTO.class);

			if (dto != null) {
			
				if(dto.getId()>0){
					crit.add(Restrictions.eq("id=",dto.getId()));
				}
				if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
					crit.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
				}
				if (dto.getLastName() != null && dto.getLastName().length() > 0) {
					crit.add(Restrictions.like("lastName", dto.getLastName() + "%"));
				}

				if (dto.getRoleId() > 0 ) {
					System.out.println("in role id");
					/*crit.setProjection(Projections.property("roleId"));
					 */
					crit.add(Restrictions.eq("roleId", dto.getRoleId()));
				}
			}
			if (pageSize > 0) {

				crit.setFirstResult((pageNo - 1) * pageSize);
				crit.setMaxResults(pageSize);
			}
			list = crit.list();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Search of UserModelHIbIMpl");
		}

		return list;
	}

	/**
	 * Search a user
	 * 
	 * @param dto
	 * @return:List of user(s)
	 * @throws ApplicationException
	 */

	public List search(UserDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}

	/**
	 * Get the List of Users
	 * 
	 * @return:List of user(s)
	 * @throws ApplicationException
	 */

	public List list() throws ApplicationException {

		return list(0, 0);
	}

	/**
	 * Get List of users with pagination
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return:List of users
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {

		List list = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria crit = session.createCriteria(UserDTO.class);

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
	 * Register a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 * @throws RecordNotFoundException
	 */

	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {

		long pk = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String msg = EmailBuilder.getUserRegistrationMessage(map);
		EmailMessage message = new EmailMessage();
		message.setTo(dto.getLogin());
		message.setSubject("Registration is succesfull for ORS project RAYS Technology");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(message);
		return pk;
	}

	/**
	 * Send the valid password to the valid loginId
	 * 
	 * @param login
	 * @return
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */

	public boolean forgetpasword(String login) throws ApplicationException, DuplicateRecordException {

		boolean flag = false;
		UserDTO dto = findbylogin(login);
		
		if (dto == null) {
			throw new DuplicateRecordException("Email id Does Not Exsist");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstName());
		map.put("lastName", dto.getLastName());

		String msg = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage message = new EmailMessage();
		message.setTo(login);
		message.setSubject("RAYS TECHNOLOGY Password Reset ");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(message);

		flag = true;
		return flag;
	}

	/**
	 * Change the password of user by ID
	 * 
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return: boolean value
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 * @throws DuplicateRecordException
	 */

	public boolean changePassword(long id, String oldPassword, String password)
			throws ApplicationException, RecordNotFoundException, DuplicateRecordException {

		boolean flag = false;
		UserDTO duplicate = findbypk(id);
		

		if (duplicate != null && duplicate.getPassword().equals(oldPassword)) {

			duplicate.setPassword(password);
			update(duplicate);
			flag = true;
		} else {
			throw new RecordNotFoundException("Record Not Found");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("oldpassword", oldPassword);
		map.put("newpassword", password);
		map.put("firstName", duplicate.getFirstName());
		map.put("lastName", duplicate.getLastName());

		String msg = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage message = new EmailMessage();
		message.setTo(duplicate.getLogin());
		message.setSubject("Rays Technology Change Password");
		message.setMessage(msg);
		message.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(message);

		return flag;
	}

	/**
	 * Authenticate a user
	 * 
	 * @param login
	 *            : String login
	 * @param password
	 *            : password
	 * @throws DatabaseException
	 */

	public UserDTO authenticate(String login, String password) {

		UserDTO dto = null;
		Session session = HibDataSource.getSession();
		Query q = session.createQuery("from UserDTO where login=? and password=?");
		q.setString(0, login);
		q.setString(1, password);

		List list = q.list();
		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		} else {
			dto = null;
		}
		return dto;
	}


}
