package com.clientserver.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * HttpSession session=request.getSession(); session.invalidate();
		 * response.sendRedirect("login.jsp");
		 */
		
		try(PrintWriter out=response.getWriter()){
			if(request.getSession().getAttribute("name")!=null) {
				request.getSession().removeAttribute("name");
				response.sendRedirect("login.jsp");
			}
			else {
				response.sendRedirect("product.jsp");
			}
		}	
	}
}
