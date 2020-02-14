package in.co.rays.ors.controller;

import java.io.IOException;
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
import in.co.rays.ors.model.UserModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * UserRegistration functionality Controller. Performs operation for Register User
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_SIGN_UP = "SignUp";

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}else if(!DataValidator.isValidName(request.getParameter("name"))){
			request.setAttribute("name", "Name be in Alphabets without space");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lname"))) {
			request.setAttribute("lname", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}else if(!DataValidator.isValidName(request.getParameter("lname"))){
			request.setAttribute("name", "Name should be in Alphabets without space");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email-Id"));
			pass = false;
		}else if(!DataValidator.isEmail(request.getParameter("email"))){
			request.setAttribute("email","Must be in formate (rahul@gmail.com)"  );
			pass= false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}else if(!DataValidator.isPassword(request.getParameter("password"))){
			request.setAttribute("password", "Must contain 1 Alphabet,1 Digit and 1special character (abc@12)");			
			pass= false;
		}
		if (DataValidator.isNull(request.getParameter("confirmpassword"))) {
			request.setAttribute("confirmpassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateofbirth"))) {
			request.setAttribute("dateofbirth", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		}else if(!DataValidator.isvalidateAge(request.getParameter("dateofbirth"))){
			request.setAttribute("dateofbirth", "User minimum age be 18 years");
			pass=false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}else if(!DataValidator.isMobileNo(request.getParameter("mobile"))){
			request.setAttribute("mobile", "must contain 10 digit start series 6-9");
			pass = false;
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
		dto.setRoleId(RoleDTO.STUDENT);

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("name")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lname")));
		dto.setLogin(DataUtility.getStringData(request.getParameter("email")));
		dto.setPassword(DataUtility.getStringData(request.getParameter("password")));
		dto.setDob(DataUtility.getDate(request.getParameter("dateofbirth")));
		dto.setGender(DataUtility.getStringData(request.getParameter("gender")));
		dto.setMobileNo(DataUtility.getStringData(request.getParameter("mobile")));

		populateDTO(dto, request);
		return dto;
	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}
	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getStringData(request.getParameter("operation"));
		
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateBean(request);

			try {
				long pk = model.registerUser(dto);
				dto.setId(pk);

				ServletUtility.setSuccessMessage("User Register SUccessfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("User Email-Id already Exist", request);
				ServletUtility.forward(getView(), request, response);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return ORSView.USER_REGISTRATION_VIEW;
	}

}
