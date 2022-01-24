package com.clientserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clientserver.dao.ConnectionUtil;
import com.clientserver.dto.Cart;

/**
 * Servlet implementation class CheckOutServlet
 */

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	ArrayList<Cart> cartList = new ArrayList<>();
	String name=request.getParameter("name");
	int total = Integer.parseInt(request.getParameter("total"));
	Connection con;
	
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	
		con = ConnectionUtil.getConnection();
		HttpSession session = request.getSession();
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
		String sql = "select amount from registration where name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			if (total < rs.getInt("amount")) {
				if (cart_list != null) {
					cart_list.removeAll(cart_list);
					/*
					 * int balance = rs.getInt("amount")-total;
					 * 
					 * sql = "UPDATE registration SET amount=? WHERE name=?";
					 * 
					 * ps = con.prepareStatement(sql); ps.setString(1, name); ps.setInt(6, balance);
					 * ps.executeUpdate();
					 */
					out.println(
							"<h3 style='color:green;text-align:center'>Completed. Visit Our Website For More Purchases.."
									+ "<a href='product.jsp'>Go to Home.</a> </h3>");
				}
				if (cart_list == null) {
					out.println("<h3 style='color:red;text-align:center'>No products avilable in Cart."
							+ "<a href='product.jsp'>Go to Home.</a> </h3>");
				}
			} else {

				out.println("<h3 style='color:red;text-align:center'>No Balance."
						+ "<a href='cart.jsp'>Go to Cart.</a> </h3>");
			}

		}

	} catch (Exception e) {
		response.sendRedirect("login.jsp");
		System.out.println(e);

	}

}

}
