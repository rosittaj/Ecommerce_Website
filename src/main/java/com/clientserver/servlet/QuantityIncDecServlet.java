package com.clientserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientserver.dto.Cart;
import com.clientserver.dao.*;
/**
 * Servlet implementation class QuantityIncDec
 */

public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter();) {
			String action=request.getParameter("action");
			int id=Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			Connection con;
			
			if (action != null && id >= 1) {

				// The action is increment.

				if (action.equals("inc")) {
					for (Cart c : cart_list) {
						if (c.getId_product() == id) {
							try {
								con = ConnectionUtil.getConnection();

								String sql = "select qty from product where id_product=?";
								PreparedStatement ps = con.prepareStatement(sql);
								ps.setInt(1, id);
								ResultSet rs = ps.executeQuery();
								while (rs.next()) {
									int quantity = c.getQuantity();
									if (quantity < rs.getInt("qty")) {
										quantity++;
										c.setQuantity(quantity);
										response.sendRedirect("cart.jsp");
									} else {
										response.sendRedirect("cart.jsp");
									}

								}

							} catch (Exception e) {
								response.sendRedirect("cart.jsp");

							}

						}
					}
				}

				// The action id decrement.

				if (action.equals("dec")) {
					for (Cart c : cart_list) {
						if (c.getId_product() == id) {

							int quantity = c.getQuantity();

							if (quantity == 1) {
								quantity = 1;
								c.setQuantity(quantity);
								response.sendRedirect("cart.jsp");
							} else {
								quantity--;
								c.setQuantity(quantity);
								response.sendRedirect("cart.jsp");
							}
						}
					}
				}
			}
		}
	}
}
