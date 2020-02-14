package in.co.rays.ors.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.util.ServletUtility;

/**
 * Welcome functionality Controller. Performs operation for Welcome
 * 
 * @author SunilOS
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet(urlPatterns="/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

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



	protected void dopost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.WELCOME_VIEW;
	}

}
