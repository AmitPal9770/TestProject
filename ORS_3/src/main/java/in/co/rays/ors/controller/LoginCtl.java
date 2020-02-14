package in.co.rays.ors.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.ors.dto.BaseDTO;
import in.co.rays.ors.dto.RoleDTO;
import in.co.rays.ors.dto.UserDTO;
import in.co.rays.ors.exception.ApplicationException;
import in.co.rays.ors.model.ModelFactory;
import in.co.rays.ors.model.RoleModelInt;
import in.co.rays.ors.model.UserModelInt;
import in.co.rays.ors.util.DataUtility;
import in.co.rays.ors.util.DataValidator;
import in.co.rays.ors.util.PropertyReader;
import in.co.rays.ors.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Authicate and Autorisation
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns="/LoginCtl")
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_LOG_OUT = "LogOut";
	public static final String OP_SIGN_UP = "SignUp";
	
@Override
protected boolean validate(HttpServletRequest request) {
		String op = DataUtility.getStringData(request.getParameter("operation"));
		Boolean pass= true;
	
	if(OP_SIGN_UP.equalsIgnoreCase(op) || OP_LOG_OUT.equalsIgnoreCase(op)){
			 return pass;
	}
	
	if(DataValidator.isNull(request.getParameter("login"))){
		request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
		
		pass=false;
	}else if(!DataValidator.isEmail(request.getParameter("login"))){
		request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
		pass=false;
	}
	
	if(DataValidator.isNull(request.getParameter("pass"))){
		request.setAttribute("pass", PropertyReader.getValue("error.require", "Password"));
		pass=false;
	}/*else if(DataValidator.isPassword(request.getParameter("pass"))){
		request.setAttribute("pass", PropertyReader.getValue("error.require", "Password"));
		pass=false;
	}*/

	return pass;
}	
	
	
	
	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setLogin(DataUtility.getStringData(request.getParameter("login")));
		dto.setPassword(DataUtility.getStringData(request.getParameter("pass")));
		
		populateDTO(dto, request);
		return dto;
	}
	
	
	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String op = DataUtility.getString(request.getParameter("operation"));
		
			if(OP_LOG_OUT.equalsIgnoreCase(op)){
				
				session.invalidate();
				ServletUtility.setSuccessMessage("User Logout Successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		
		ServletUtility.forward(getView(), request, response);
	}
	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String op = DataUtility.getStringData(request.getParameter("operation"));

		UserModelInt model = ModelFactory.getInstance().getUserModel();
		RoleModelInt rmodel = ModelFactory.getInstance().getRoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			
			UserDTO dto = (UserDTO) populateBean(request);

			try {
				dto = model.authenticate(dto.getLogin(), dto.getPassword());
		//		ServletUtility.setBean(dto, request);

				if (dto != null) {
					session.setAttribute("user", dto);
					session.setAttribute("student", dto);
					long rollid = dto.getRoleId();
					RoleDTO dto1 = rmodel.findbypk(rollid);

					if (dto1 != null) {
						session.setAttribute("role", dto1.getName());
					}
					
					//CODE OF URI
					
					String str = (String)session.getAttribute("URI");
					if(str== null || "null".equalsIgnoreCase(str)){
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;	
					}else{
						ServletUtility.redirect(str, request, response);
						return;
					}
		
				}else {

					dto = (UserDTO) populateBean(request);
			//		ServletUtility.setBean(dto, request);
					ServletUtility.setErrorMessage("Invalid Login and Password", request);
				}

				
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
