package in.co.rays.ors.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.ServletUtility;

/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

public abstract class BaseCtl extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_UPDATE = "Update";
	public static final String OP_RESET = "Reset";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_DELETE = "Delete";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
//	public static final String OP_LOG_OUT = "Logout";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";
	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	protected BaseDTO populateBean(HttpServletRequest request) {
		
		return null;
	}

	/**
	 * Populates Generic attributes in DTO
	 * 
	 * @param dto
	 * @param request
	 * @return  object of the bean
	 */
	protected BaseDTO populateDTO(BaseDTO dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserDTO userbean = (UserDTO) request.getSession().getAttribute("user");
		if (userbean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}
		dto.setCreatedby(createdBy);
		dto.setModifiedby(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));
		if (cdt > 0) {
			dto.setCreateddatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreateddatetime(DataUtility.getCurrentTimestamp());
		}
		dto.setModifieddatetime(DataUtility.getCurrentTimestamp());
		return dto;
	}


	/**
	 * Call the service method of this Controller
	 * 
	 * @return
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in basectllllllllllllllllll");
		// Load the preloaded data required to display at HTML form
		preload(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("-----------"+op);
		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
			System.out.println("1111111111111111222222222225555555555");
			// Check validation, If fail then send back to page with error
			// messages
			
			if (!validate(request)) {
				System.out.println("111111111111");
				BaseDTO bean = (BaseDTO) populateBean(request);
				System.out.println("=======-----======)))"+bean);
				ServletUtility.setDto(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}	
		System.out.println("============>>>>>>>");
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();

}
