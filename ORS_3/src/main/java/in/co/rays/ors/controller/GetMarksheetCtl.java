package in.co.rays.ors.controller;

import java.io.IOException;
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
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Servlet implementation class GetMarksheetCtl
 */

/**
 * Get Marksheet functionality Controller. Performs operation for Getting Marksheet
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns="/ctl/GetMarksheetCtl")
public class GetMarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll No"));
			pass = false;
		} /*else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.rollno", "Roll No"));
			pass = false;
		}*/
		System.out.println("=======>>>"+pass);
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		MarksheetDTO dto = new MarksheetDTO();
		dto.setId(DataUtility.getInt(request.getParameter("id")));
		dto.setRollNo(DataUtility.getStringData(request.getParameter("rollNo")));
		dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		dto.setStudentname(DataUtility.getStringData(request.getParameter("studentname")));
		dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		dto.setMaths(DataUtility.getInt(request.getParameter("maths")));
		dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
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

		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		MarksheetDTO dto = (MarksheetDTO) populateBean(request);

			
		if (OP_GO.equalsIgnoreCase(op)) {
			try {

				dto = model.findbyRollNo(dto.getRollNo());

				if (dto != null) {
					ServletUtility.setDto(dto, request);
					ServletUtility.forward(getView(), request, response);
				} else {
					ServletUtility.setErrorMessage("Roll Number does not Exist", request);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}

}
