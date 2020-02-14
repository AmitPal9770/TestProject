package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.StudentDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.StudentModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Student List functionality Controller. Performs operation for search, Edit, delete
 * and get Student List
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */



@WebServlet(urlPatterns = "/ctl/StudentListCtl")
public class StudentListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		StudentDTO dto = new StudentDTO();
		dto.setId(DataUtility.getInt(request.getParameter("id")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lastName")));
		dto.setEmailid(DataUtility.getStringData(request.getParameter("emailid")));

		return dto;
	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		int pageNo = 1;
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		StudentDTO dto = new StudentDTO();
		List list;
		List nextlist ;
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		try {
			list = model.search(dto, pageNo, pageSize);
			nextlist = model.search(dto, pageNo+1, pageSize);
		if (list.size() == 0 || list == null) {
				ServletUtility.setErrorMessage("No record Found", request);
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		
		request.setAttribute("next-list", nextlist.size());
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);


	}
	
	/**
	 * Contains Display logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list;
		List nextlist;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		StudentDTO dto = (StudentDTO) populateBean(request);
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		String op = DataUtility.getStringData(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
		
		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op) || OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			pageNo = 1;

			if (ids != null && ids.length > 0) {
				StudentDTO deletedto = new StudentDTO();

				for (String id : ids) {
					deletedto.setId(DataUtility.getInt(id));
					try {
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Student Data Deleted Successfully", request);
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.handleException(e, request, response);
						return;
					}
				}
			}else {
				ServletUtility.setErrorMessage("Select One Record to Delete", request);
			}
		}

		try {
			list = model.search(dto, pageNo, pageSize);
			nextlist = model.search(dto, pageNo+1, pageSize);
		
			if (list.size() == 0 || list == null) {
				ServletUtility.setErrorMessage("No record Found", request);
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}

		request.setAttribute("next-list", nextlist.size());
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_LIST_VIEW;
	}

}
