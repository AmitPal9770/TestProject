package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModelInt;
import in.co.rays.ors.model.CourseModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Course functionality Controller. Performs operation for add, update, delete.
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/CourseCtl")
public class CourseCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.Name", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.Name", "Description"));
			pass = false;
		}

		return pass;
	}

	protected BaseDTO populateBean(HttpServletRequest request) {
		CourseDTO dto = new CourseDTO();

		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getStringData(request.getParameter("name")));
		dto.setDuration(DataUtility.getStringData(request.getParameter("duration")));
		dto.setDescription(DataUtility.getStringData(request.getParameter("description")));

		populateDTO(dto, request);
		return dto;

	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();

		if (id > 0) {
			try {
				CourseDTO dto = model.findbypk(id);
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

		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		CourseDTO dto = (CourseDTO) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if (id>0) {	
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Course is Updated Successfully", request);
				} else {
					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("Course is Added Successfully", request);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Course ALready Exist", request);
			}
		}
		else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_VIEW;
	}

}
