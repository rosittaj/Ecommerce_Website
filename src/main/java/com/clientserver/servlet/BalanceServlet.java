package com.clientserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientserver.dao.ConnectionUtil;

public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		int total = Integer.parseInt(request.getParameter("total"));
		Connection con;
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			con = ConnectionUtil.getConnection();
			
			//Query for select the amount in the current user.
			
			String sql = "select amount from registration where name=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("amount"));
				if (total < rs.getInt("amount")) {
					int amount = rs.getInt("amount");

					out.println("<h3 style='color:green;text-align:center'>Buy Now."
							+ "<a href='cart.jsp'>Go to Cart.</a> </h3>");
				} else {
					out.println("<h3 style='color:green;text-align:center'>No Balance."
							+ "<a href='cart.jsp'>Go to Cart.</a> </h3>");
				}
			}
		} catch (Exception e) {
			response.sendRedirect("login.jsp");
			System.out.println(e);
		}
	}
}