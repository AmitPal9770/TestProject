package in.co.rays.ors.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ors.controller.BaseCtl;
import in.co.rays.ors.dto.BaseDTO;

/**
 * This class provides utility operation for Servlet container like forward,
 * redirect, handle generic exception, manage success and error message, manage
 * default dto and List, manage pagination parameters
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class ServletUtility {

	 /**
     * Forward to given JSP/Servlet
     * 
     * @param page
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
   	
	public static void forward(String page,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException
	{
	RequestDispatcher rd=req.getRequestDispatcher(page);
	rd.forward(req, resp);
		
	}
	
		
	 /**
     * Redirect to given JSP/Servlet
     * 
     * @param page
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    	
	public static void redirect(String page,HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException
	{
		resp.sendRedirect(page);
	}
	
	
	 /**
     * Redirect to Application Error Handler Page
     * 
     * @param e
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    
	public static void handleException(Exception e,HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException
	{
		   req.setAttribute("exception", e);
	        resp.sendRedirect("/OnlineResultSystemProject3/ErrorCtl");
	}
	
	/**
     * Gets error message from request
     * 
     * @param property
     * @param request
     * @return
     */
public static String getErrormessage(String property,HttpServletRequest req)
	{
		String val=(String)req.getAttribute(property);
		if(val==null)
		{
			return "";
		}
		else
		{
			return val;
		}
	
	}
	

/**
 * Gets error message from request
 * @parameter  request
 * @return String value
 */
public static String getErrorMessage(HttpServletRequest request) {
    String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
    if (val == null) {
        return "";
    } else {
        return val;
    }
}


	
	 /**
     * Gets a message from request
     * parameter property,request 
     * return String value
     */
    public String getMessage(String property,HttpServletRequest req)
    {
    	 String val = (String) req.getAttribute(property);
         if (val == null) {
             return "";
         } 
         else
         {
             return val;
         }
    }
	
  
    /**
     * Sets error message to request
     * @parameter message
     * @parameter request
     */
    public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_ERROR, msg);
    }
    
    /**
     * Sets success message to request 
     * @parameter message
     * @param request
     */
    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
    }

    /**
     * Gets success message from request
     * @parameter request
     * @return String value
     */
    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
        if (val == null) {
            return "";
        } else
        {
            return val;
        }
    }

    
    
    /**
     * Sets default dto to request
     * @parameter dto
     * @param request
     */
    public static void setDto(BaseDTO dto, HttpServletRequest request) {
        request.setAttribute("dto", dto);
    }
    
    /**
     * Gets default dto from request
     * @param request
     * @return
     */

    public static BaseDTO getDto(HttpServletRequest request) {
        return (BaseDTO) request.getAttribute("dto");
    }

       
    /**
     * Get request parameter to display. If value is null then return empty string
     * @param property
     * @param request
     * @return String
     */

    public static String getParameter(String property,
            HttpServletRequest request) {
        String val = request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    
    /**
     * Sets default List to request
     * 
     * @param list
     * @param request
     */
    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }

    /**
     * Gets default list from request
     * 
     * @param request
     * @return
     */
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

    /**
     * Sets Page Number for List pages
     * 
     * @param pageNo
     * @param request
     */
    public static void setPageNo(int pageNo, HttpServletRequest request) {
    	System.out.println("page no in servlet utility="+pageNo);
        request.setAttribute("pageNo", pageNo);
    }

    /**
     * Gets Page Number for List pages
     * 
     * @param request
     * @return
     */
    public static int getPageNo(HttpServletRequest request) {
    	  return  (Integer) request.getAttribute("pageNo");
    }

    /**
     * Sets Page Size for list pages
     * 
     * @param pageSize
     * @param request
     */
    public static void setPageSize(int pageSize, HttpServletRequest request) {
        request.setAttribute("pageSize", pageSize);
    }

    /**
     * Gets Page Size for List pages
     * 
     * @param request
     * @return
     */
    public static int getPageSize(HttpServletRequest request) {
    	return  (Integer) request.getAttribute("pageSize");
    }
    
    
   


       
    
    
    
	
}
