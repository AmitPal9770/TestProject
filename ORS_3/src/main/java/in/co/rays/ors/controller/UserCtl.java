package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.exception.RecordNotFoundException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.RoleModelInt;
import in.co.rays.ors.model.UserModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * User functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/UserCtl")
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List list = model.list();
			request.setAttribute("rolelist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("name"))) {
			request.setAttribute("name", "Name should be in Alphabets without space");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lname"))) {
			request.setAttribute("lname", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("lname"))) {
			request.setAttribute("name", "Name should be in Alphabets without space");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email-Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", "Must be in formate (rahul@gmail.com)");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Must contain 1 Alphabet,1 Digit and 1special character (abc@12)");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmpassword"))) {
			request.setAttribute("confirmpassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dateofbirth"))) {
			request.setAttribute("dateofbirth", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isvalidateAge(request.getParameter("dateofbirth"))) {
			request.setAttribute("dateofbirth", "User minimum age be 18 years");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "must contain 10 digit start series 6-9");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("role"))) {
			request.setAttribute("role", PropertyReader.getValue("error.require", "Role"));
		}
		
		if (!request.getParameter("password").equals(request.getParameter("confirmpassword")) && !"".equals(request.getParameter("confirmpassword"))){	
			request.setAttribute("confirmpassword", PropertyReader.getValue("error.confirmpassword", "New "));
			pass = false;
		}
		
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("name")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lname")));
		dto.setLogin(DataUtility.getStringData(request.getParameter("email")));
		dto.setPassword(DataUtility.getStringData(request.getParameter("password")));
		dto.setDob(DataUtility.getDate(request.getParameter("dateofbirth")));
		dto.setGender(DataUtility.getStringData(request.getParameter("gender")));
		dto.setMobileNo(DataUtility.getStringData(request.getParameter("mobile")));
		dto.setRoleId(DataUtility.getLong(request.getParameter("role")));

		populateDTO(dto, request);
		return dto;
	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		if (id > 0) {

			try {
				UserDTO dto = model.findbypk(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);

	}
	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getStringData(request.getParameter("operation"));
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		UserDTO dto = (UserDTO) populateBean(request);
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {

				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("User is Updated Successfully", request);

				} else {
					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("User is Added Successfully", request);

				}
	//			ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("User Email-Id already register", request);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(OP_CANCEL.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
