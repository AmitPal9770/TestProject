package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
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
 * Forget Password functionality Controller. Performs operation for reset the password
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */


@WebServlet(urlPatterns = "/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_GO = "Go";

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Email-Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Invalid"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(DataUtility.getStringData(request.getParameter("firstName")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("lastName")));
		dto.setLogin(DataUtility.getStringData(request.getParameter("login")));

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
		UserDTO dto = (UserDTO) populateBean(request);
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				model.forgetpasword(dto.getLogin());
				ServletUtility.setSuccessMessage("Password is send to your register Email-Id", request);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage(e.getMessage(), request);

			} catch (RecordNotFoundException e) {
				e.printStackTrace();

			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
