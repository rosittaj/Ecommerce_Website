package com.clientserver.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clientserver.dto.Cart;

/**
 * Servlet implementation class RemoveServlet
 */

public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String id=request.getParameter("id");
			if(id != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cart_list !=null) {
					for(Cart c:cart_list) {
						if(c.getId_product()==Integer.parseInt(id));
						cart_list.remove(cart_list.indexOf(c));
						response.sendRedirect("cart.jsp");
						break;
					}
				}
			}
			
			else {
				response.sendRedirect("cart.jsp");
			}
			
		}catch(Exception e) {
			response.sendRedirect("cart.jsp");
		}
		
		
		
	}

}
