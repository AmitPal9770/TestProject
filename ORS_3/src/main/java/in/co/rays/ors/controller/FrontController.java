package in.co.rays.ors.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.ors.util.ServletUtility;

@WebFilter(urlPatterns = {"/ctl/*", "/doc/*" })
public class FrontController implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession(true);
	
		
		if((session.getAttribute("user") == null) || (session.getAttribute("student")== null)){
			request.setAttribute("message", "Your Session is experied ..!! Please Login again");
	
			String str = request.getRequestURI();
			session.setAttribute("URI", str);

			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			return;
		}else{
			System.out.println("session experie");
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig confiq) throws ServletException {

	}

	public void destroy() {

	}

}
