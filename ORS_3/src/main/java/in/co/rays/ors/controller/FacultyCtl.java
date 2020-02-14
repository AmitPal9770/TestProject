package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.FacultyDTO;
import in.co.rays.ors.dto.SubjectDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModelInt;
import in.co.rays.ors.model.CourseModelInt;
import in.co.rays.ors.model.FacultyModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.SubjectModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		CourseModelInt cmodel = ModelFactory.getInstance().getCourseModel();
		SubjectModelInt smodel = ModelFactory.getInstance().getSubjectModel();

		try {
			List clist = model.list();
			List cclist = cmodel.list();
			List slist = smodel.list();

			request.setAttribute("collegelist", clist);
			request.setAttribute("courselist", cclist);
			request.setAttribute("subjectlist", slist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CollegeName"))) {
			request.setAttribute("CollegeName", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("CourseName"))) {
			request.setAttribute("CourseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("SubjectName"))) {
			request.setAttribute("SubjectName", PropertyReader.getValue("error.require", "Student Name"));
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
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateofjoining"))) {
			request.setAttribute("dateofjoining", PropertyReader.getValue("error.require", "Date of Joining"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.name", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("emailid"))) {
			request.setAttribute("emailid", PropertyReader.getValue("error.require", "Email-Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("emailid"))) {
			request.setAttribute("emailid", PropertyReader.getValue("error.email", "Email-Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getValue("error.mobile", "Mobile Number"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		FacultyDTO dto = new FacultyDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setCollegeid(DataUtility.getLong(request.getParameter("CollegeName")));
		dto.setCourseId(DataUtility.getLong(request.getParameter("CourseName")));
		dto.setSubjectId(DataUtility.getLong(request.getParameter("SubjectName")));
		dto.setFirstName(DataUtility.getStringData(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lastName")));
		dto.setGender(DataUtility.getStringData(request.getParameter("gender")));
		dto.setDateofjoining(DataUtility.getDate(request.getParameter("dateofjoining")));
		dto.setQualification(DataUtility.getStringData(request.getParameter("qualification")));
		dto.setEmailId(DataUtility.getStringData(request.getParameter("emailid")));
		dto.setMobileno(DataUtility.getStringData(request.getParameter("mobileno")));

		populateDTO(dto, request);
		return dto;
	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long id = DataUtility.getLong(request.getParameter("id"));
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		if (id > 0) {
			try {
				FacultyDTO dto = model.findbypk(id);
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
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		FacultyDTO dto = (FacultyDTO) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if(id>0){
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("Faculty is Updated Successfully", request);
				}else{
					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("Faculty is Added Successfully", request);
				}				
	//			ServletUtility.setDto(dto, request);
				// dto.setId(pk);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Faculty ALready Exsist", request);
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
