package com.clientserver.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientserver.dao.DBUtil;
import com.clientserver.dto.User;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Store all user enter values to variables.
		
		final String name = request.getParameter("name");
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		final String phno = request.getParameter("phno");
		final String country = request.getParameter("country");
		final String amount = request.getParameter("amount");
	 
	
	    //Create a user object for store all values.
	    
		User user=new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhno(phno);
		user.setCountry(country);
		user.setAmount(amount);
		
		//Checking the user is stored in DataBase
		
		int rowCount=DBUtil.saveUser(user);
		if(rowCount>0) {
			response.sendRedirect("login.jsp");
		}
		else {
			response.sendRedirect("register.jsp");;
		}
		
	}
}
