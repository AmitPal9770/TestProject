package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.model.MarksheetModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;


/**
 * Marksheet Merit List functionality Controller. Performs operation for Merit list
 * and get College List
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */



@WebServlet(urlPatterns = "/ctl/MarksheetMeritListCtl")
public class MarksheetMeritListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		List list;

		try {
			list = model.getMeritlist(pageNo, pageSize);
			System.out.println("======================>>>>"+list.size());
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
	}
	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getStringData(request.getParameter("operation"));
		
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		List list;

		if(OP_BACK.equalsIgnoreCase(op)){
			ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
			return;
		}
		
		
		try {
			list = model.getMeritlist(pageNo, pageSize);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record Found", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

}
