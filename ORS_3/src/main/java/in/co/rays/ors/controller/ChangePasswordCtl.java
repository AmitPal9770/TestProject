package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

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
 * Change PAssword functionality Controller. Performs operation for change password of 
 * current user
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns="/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       public static final String OP_CHANGE_MY_PROFILE = "Change Profile"; 

       @Override
	protected boolean validate(HttpServletRequest request) {
		String op = DataUtility.getStringData(request.getParameter("operation"));
		boolean pass = true;
		
		if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
			return pass ;
		}
		
		if(DataValidator.isNull(request.getParameter("oldpassword"))){
			request.setAttribute("oldpassword", PropertyReader.getValue("error.require", "Old Password"));
			System.out.println("1111");
			pass = false;

		}else if (!DataValidator.isPassword(request.getParameter("oldpassword"))){
			request.setAttribute("oldpassword", "Password is Incorrect ");
			System.out.println("2222");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("newpassword"))){
			request.setAttribute("newpassword", PropertyReader.getValue("error.require", "New Password"));
			System.out.println("3333");
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("newpassword"))){
			request.setAttribute("newpassword", PropertyReader.getValue("error.password", "New Password"));
			System.out.println("4444");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			System.out.println("5555");
			pass = false;
		}
		if (!request.getParameter("newpassword").equals(request.getParameter("confirmPassword")) && !"".equals(request.getParameter("confirmpassword"))){	
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmpassword", "New "));
			System.out.println("6666");
			pass = false;
		}
		System.out.println("+++========pass =========="+pass);
		return pass;
	}
	
	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
			UserDTO dto = new UserDTO();
			
		dto.setFirstName(DataUtility.getStringData(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getStringData(request.getParameter("lastName")));
		dto.setPassword(DataUtility.getString(request.getParameter("oldpassword")));
		dto.setPassword(DataUtility.getString(request.getParameter("newpassword")));
		populateDTO(dto, request);
		return dto;
	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		long id = userdto.getId();
	//	System.out.println("===id====="+id);
		String op = DataUtility.getStringData(request.getParameter("operation"));
				
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		UserDTO dto =(UserDTO) populateBean(request);
		
		String newPassword = (String)request.getParameter("newpassword");
		
		System.out.println("===========password===============>>"+userdto.getPassword());
		System.out.println("===========password===============>>"+newPassword);
		
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			try {
				boolean flag = model.changePassword(id,userdto.getPassword(),newPassword);
			if(flag == true){
				
				dto = model.findbylogin(userdto.getLogin());
				session.setAttribute("user", dto);
		//		ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Password is changed Successfully", request);
			}
			
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		}
		else if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
