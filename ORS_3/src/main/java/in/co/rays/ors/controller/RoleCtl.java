package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.sun.mail.iap.Response;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.RoleModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Role functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/RoleCtl")
public class RoleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		RoleDTO dto = new RoleDTO();
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Role Name"));
			pass = false;
		} else if (!DataValidator.isValidName(request.getParameter("name"))) {
			request.setAttribute("name", "contain only Alphabets without Space");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", "contain only Alphabets");
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		RoleDTO dto = new RoleDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getStringData(request.getParameter("name")));
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
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		if (id > 0) {
			try {
				RoleDTO dto = model.findbypk(id);
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

		String op = DataUtility.getStringData(request.getParameter("operation"));
		RoleDTO dto = (RoleDTO) populateBean(request);
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if (dto.getId() > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Role is Update Successfully ", request);

				} else {
					long pk = model.add(dto);
				//	dto.setId(pk);
					ServletUtility.setSuccessMessage("Role is Added Successfully ", request);

				}

				
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Role already Exists", request);
			}

		}else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}
