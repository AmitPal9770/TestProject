package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.hql.ast.tree.BooleanLiteralNode;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CourseModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.SubjectModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Subject functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		try {
			List list = model.list();
			request.setAttribute("courselist", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CourseName"))) {
			request.setAttribute("CourseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectName"))) {
			request.setAttribute("subjectName", PropertyReader.getValue("error.require", "Subject Name"));
			pass= false;
		} else if (!DataValidator.isName(request.getParameter("subjectName"))) {
			request.setAttribute("subjectName", PropertyReader.getValue("error.Name", "Subject Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass=false;
		} else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.Name", "Description"));
			pass= false;
		}
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		SubjectDTO dto = new SubjectDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setCourseId(DataUtility.getLong(request.getParameter("CourseName")));
		dto.setSubjectName(DataUtility.getStringData(request.getParameter("subjectName")));
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
		SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();

		if (id > 0) {
			try {
				SubjectDTO dto = model.findbypk(id);
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
		SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();

		SubjectDTO dto = (SubjectDTO) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if(id>0){
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Subject is Updated Successfully", request);
				}else{
					long pk = model.add(dto);

					ServletUtility.setSuccessMessage("Subject is Added Successfully", request);
				}

				// dto.setId(pk);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Student ALready Exsist", request);
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return ORSView.SUBJECT_VIEW;
	}

}
