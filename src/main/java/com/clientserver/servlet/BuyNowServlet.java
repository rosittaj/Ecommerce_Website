package com.clientserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clientserver.dto.Cart;

public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));

			Cart cm = new Cart();
			cm.setId_product(id);
			cm.setQuantity(1);

			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			if (cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("cart.jsp");
			} else {
				cartList = cart_list;
				boolean exist = false;
				for (Cart c : cartList) {
					if (c.getId_product() == id) {
						exist = true;
						out.println(
								"<h3 style='color:red;text-align:center'>Item already exit in the cart.<a href='cart.jsp'>Go to Cart.</a> </h3>");
					}
				}
				if (!exist) {
					cartList.add(cm);
					response.sendRedirect("cart.jsp");
				}
			}
		}
	}
}
