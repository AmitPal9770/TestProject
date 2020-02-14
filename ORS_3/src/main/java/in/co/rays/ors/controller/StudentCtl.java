package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.StudentModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;


/**
 * Student functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/StudentCtl")
public class StudentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		List list = null;
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		try {
			list = model.list();
			request.setAttribute("collegelist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}



	
	protected boolean validate(HttpServletRequest request) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiii");
		UserDTO dto = new UserDTO();
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CollegeName"))) {
			request.setAttribute("CollegeName", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isvalidateAge(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Student"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile No"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("emailid"))) {
			request.setAttribute("emailid", PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("emailid"))) {
			request.setAttribute("emailid", PropertyReader.getValue("error.email", "Email Id"));
			pass = false;
		}
			System.out.println("===================>>>>>"+ pass);
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		StudentDTO dto = new StudentDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lastName")));
		dto.setCollegeId(DataUtility.getLong(request.getParameter("CollegeName")));
		// dto.setCollegeName(DataUtility.getStringData(request.getParameter("collegeName")));
		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		dto.setMobileNo(DataUtility.getStringData(request.getParameter("mobileNo")));
		dto.setEmailid(DataUtility.getStringData(request.getParameter("emailid")));

		populateDTO(dto, request);
		return dto;
	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		if (id > 0) {

			try {
				StudentDTO dto = model.findByPK(id);
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
		StudentDTO dto = (StudentDTO) populateBean(request);
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Student Record successfully Updated", request);
				} else {
					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("Student Record successfully Added", request);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Student Record already Exist", request);
			}

		}else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return ORSView.STUDENT_VIEW;
	}

}
