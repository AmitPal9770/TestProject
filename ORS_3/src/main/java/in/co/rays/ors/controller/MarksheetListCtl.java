package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.MarksheetDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.model.MarksheetModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.StudentModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Marksheet List functionality Controller. Performs operation for search, Edit,
 * delete and get College List
 * 
 * @author SunilOS
 * @version 1.0 Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			List list = (List) model.list();
			request.setAttribute("slist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		MarksheetDTO dto = new MarksheetDTO();
		dto.setRollNo(DataUtility.getStringData(request.getParameter("rollNo")));
		dto.setStudentId(DataUtility.getLong(request.getParameter("studentname")));

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

		List list;
		List nextlist;		
		MarksheetDTO dto = new MarksheetDTO();
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();

		try {
			list = model.search(dto, pageNo, pageSize);
			nextlist = model.search(dto, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record Found", request);
				return;
			}
			
			  request.setAttribute("nextlist", nextlist);
			  ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		List nextlist;
		String op = DataUtility.getStringData(request.getParameter("operation"));
//		System.out.println("--post----->> " + op);
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		MarksheetDTO dto = (MarksheetDTO) populateBean(request);
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
			System.out.println("========next========"+pageNo);
			
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op) || OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {
			System.out.println("inside delete");
			String[] ids = request.getParameterValues("ids");
			pageNo = 1;
			if (ids != null && ids.length > 0) {

				MarksheetDTO deletedto = new MarksheetDTO();
				for (String id : ids) {
					deletedto.setId(DataUtility.getInt(id));
					try {
				//		System.out.println("delete");
						model.delete(deletedto);
				//		System.out.println("deleted");
						ServletUtility.setSuccessMessage("Marksheet Deleted Successfully", request);
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.handleException(e, request, response);
						return;
					}
				}
			} else {
				ServletUtility.setErrorMessage("Select one Record to delete", request);
			}
		}

		try {
			list = model.search(dto, pageNo, pageSize);
			nextlist = model.search(dto, pageNo+1, pageSize);
			
			if (list.size() == 0 || list == null && OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found", request);
			}
			System.out.println("=========list size====="+list.size());
			System.out.println("=========list size====="+nextlist.size());
			

			request.setAttribute("nextlist", nextlist);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
//			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
	ServletUtility.forward(getView(), request, response);	
	}

	@Override
	protected String getView() {

		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
