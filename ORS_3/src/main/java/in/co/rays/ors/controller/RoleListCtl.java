package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.RoleModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Role List functionality Controller. Performs operation for search, Edit, delete
 * and get College List
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/RoleListCtl")
public class RoleListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		try {
			List list = (List) model.list();
			request.setAttribute("slist", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		RoleDTO dto = new RoleDTO();
		dto.setId(DataUtility.getInt(request.getParameter("rolename")));
	//	dto.setName(DataUtility.getStringData(request.getParameter("name")));

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

		RoleDTO dto = new RoleDTO();
		List list;
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		try {
			list = model.search(dto, pageNo, pageSize);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list.size() == 0 || list == null) {
			ServletUtility.setErrorMessage("No record Found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}
	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getStringData(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
		List list;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		RoleDTO dto =(RoleDTO) populateBean(request);
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op) || OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			pageNo = 1;
			if (ids != null && ids.length > 0 ) {
				
				RoleDTO deltedto = new RoleDTO();
				for (String id : ids) {
					deltedto.setId(DataUtility.getInt(id));
					try {
						model.delete(deltedto);
						ServletUtility.setSuccessMessage("Role Deleted Successfully", request);
					} catch (ApplicationException e) {
						e.printStackTrace();
						ServletUtility.handleException(e, request, response);
						return;
					}
				}

			} else {
				ServletUtility.setErrorMessage("No record found", request);
			}
		}

		try {
			list = model.search(dto, pageNo, pageSize);
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
		// TODO Auto-generated method stub
		return ORSView.ROLE_LIST_VIEW;
	}

}
