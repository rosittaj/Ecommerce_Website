package com.clientserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.clientserver.dao.ConnectionUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		HttpSession session=request.getSession();
		Connection con;
		
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			
			// Checking the email & password in the registration table

			con = ConnectionUtil.getConnection();
			String sql = "select * from registration where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				session.setAttribute("name", rs.getString("name"));
				response.sendRedirect("product.jsp");
			} else {
				out.println(
						"<h3 style='color:red;text-align:center'>Not A User/Invalid username and password.<a href='login.jsp'>Go to Login.</a> </h3>");
			}
		}
		
				/*User user=DBUtil.loginUser(email,password);
				if(user!=null) {
					request.getSession().setAttribute("email",email);
					response.sendRedirect("product.jsp");
					
				}
				else {
					response.sendRedirect("login.jsp");
					
				}
			*/
			catch (ClassNotFoundException | SQLException e) {
				
				 e.printStackTrace(); System.out.print(e); 
				 }
	
		}
		}	

		
		
		
		
		
		/*
		 * Login login=new Login();
		 * 
		 * login.setEmail(email); login.setPassword(password);
		 * 
		 * DBUtil.loginUser(login);
		 */
		
		
