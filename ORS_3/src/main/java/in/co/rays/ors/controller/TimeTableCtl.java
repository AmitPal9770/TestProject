package in.co.rays.ors.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.CollegeDTO;
import in.co.rays.ors.dto.CourseDTO;
import in.co.rays.ors.dto.TimeTableDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.model.CollegeModelInt;
import in.co.rays.ors.model.CourseModelInt;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.SubjectModelInt;
import in.co.rays.ors.model.TimeTableModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * TimeTable functionality Controller. Performs operation for add, update, delete
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/TimeTableCtl")
public class TimeTableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModelInt cmodel = ModelFactory.getInstance().getCourseModel();
		SubjectModelInt smodel = ModelFactory.getInstance().getSubjectModel();

		try {
			List cclist = cmodel.list();
			List slist = smodel.list();

			request.setAttribute("courselist", cclist);
			request.setAttribute("subjectlist", slist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CourseName"))) {
			request.setAttribute("CourseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("SubjectName"))) {
			request.setAttribute("SubjectName", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		TimeTableDTO dto = new TimeTableDTO();

		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setSubjectId(DataUtility.getInt(request.getParameter("SubjectName")));
		dto.setCourseId(DataUtility.getInt(request.getParameter("CourseName")));
		dto.setSemester(DataUtility.getStringData(request.getParameter("semester")));
		dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		dto.setExamTime(DataUtility.getStringData(request.getParameter("examTime")));

		populateDTO(dto, request);
		return dto;

	}
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		TimeTableModelInt model = ModelFactory.getInstance().getTimeTableModel();

		if (id > 0) {
			try {
				TimeTableDTO dto = model.findbypk(id);
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

		TimeTableModelInt model = ModelFactory.getInstance().getTimeTableModel();
		TimeTableDTO dto = (TimeTableDTO) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {

				TimeTableDTO t1 = model.checkBycds(dto.getCourseId(), dto.getSemester(), dto.getExamDate());
				TimeTableDTO t2 = model.checkBycss(dto.getCourseId(), dto.getSubjectId(), dto.getSemester());

				if (t1 != null || t2 != null) {
					throw new DuplicateRecordException("TimeTable already Exist");
				}
				if (id > 0) {
					model.update(dto);
					ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("TimeTable is Updated Successfully", request);
				
				} else {
					long pk = model.add(dto);
				//	ServletUtility.setDto(dto, request);
					ServletUtility.setSuccessMessage("TimeTable is Added Successfully", request);
				
				}
				// dto.setId(pk);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("TimeTable Already Exist", request);
			}
		}
		else if (OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}
