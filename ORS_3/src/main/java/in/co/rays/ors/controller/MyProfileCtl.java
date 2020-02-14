package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.exception.DuplicateRecordException;
import in.co.rays.ors.exception.RecordNotFoundException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.UserModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;


/**
 * My Profile functionality Controller. Performs operation for User Profile Edit
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns = "/ctl/MyProfileCtl")
public class MyProfileCtl extends BaseCtl {
	public static final String OP_CHANGE_MY_PASSWORD = "Change Password";
	private static final long serialVersionUID = 1L;

	
    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;
        String op = DataUtility.getString(request.getParameter("operation"));

        if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
            return pass;
        }

        if (DataValidator.isNull(request.getParameter("firstName"))) {

            request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("gender"))) {
            request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
            pass = false;
        }
        else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
        	request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Invalid"));
        	pass = false;
			
		}
        if (DataValidator.isNull(request.getParameter("dob"))) {
            request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        }

        return pass;

    }

    @Override
    protected BaseDTO populateBean(HttpServletRequest request) {


    	UserDTO dto = new UserDTO();

        dto.setId(DataUtility.getLong(request.getParameter("id")));
        dto.setLogin(DataUtility.getString(request.getParameter("login")));
        dto.setFirstName(DataUtility.getString(request .getParameter("firstName")));
        dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
        dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
        dto.setGender(DataUtility.getString(request.getParameter("gender")));
        dto.setDob(DataUtility.getDate(request.getParameter("dob")));

        populateDTO(dto, request);
        return dto;
    }

	
	

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		long id = userdto.getId();

		UserModelInt model = ModelFactory.getInstance().getUserModel();
		if (id > 0) {

			try {
				UserDTO dto = model.findbypk(id);
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
	 * Contains Display logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		long id = userdto.getId();

		String op = DataUtility.getStringData(request.getParameter("operation"));
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {

			UserDTO dto = (UserDTO) populateBean(request);

			try {
				if (id > 0) {
					userdto.setFirstName(dto.getFirstName());
					userdto.setLastName(dto.getLastName());
					userdto.setGender(dto.getGender());
					userdto.setMobileNo(dto.getMobileNo());
					userdto.setDob(dto.getDob());
					model.update(dto);
				}
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Profile is Update Successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login Id is already Exist", request);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
			
		}else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)){
			ServletUtility.redirect(OP_CHANGE_MY_PASSWORD, request, response);
			return;
		}

	ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}
