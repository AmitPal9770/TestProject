package in.co.rays.ors.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModelInt;
import in.co.rays.ors.model.FacultyModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * College functionality Controller. Performs operation for add, update, delete
 * and get College
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.Name", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		} else if (!DataValidator.isAddress(request.getParameter("address"))) {
			request.setAttribute("address", "Enter full Address");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		CollegeDTO dto = new CollegeDTO();

		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getStringData(request.getParameter("name")));
		dto.setAddress(DataUtility.getStringData(request.getParameter("address")));
		dto.setState(DataUtility.getStringData(request.getParameter("state")));
		dto.setCity(DataUtility.getStringData(request.getParameter("city")));
		dto.setMobileNo(DataUtility.getStringData(request.getParameter("mobileNo")));

		populateDTO(dto, request);
		return dto;

	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		if (id > 0) {
			try {
				CollegeDTO dto = model.findbypk(id);
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
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();

		CollegeDTO dto = (CollegeDTO) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("College is Updated Successfully", request);
				} else {

					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("College is Added Successfully", request);
				}
				// dto.setId(pk);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("College ALready Exsist", request);
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COLLEGE_VIEW;
	}

}
