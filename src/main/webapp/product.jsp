<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.*"%><%@ page import="java.util.*"%>
<%@ page import="com.clientserver.dao.*"%>
<%@ page import="com.clientserver.dto.*"%>
<%@ page import="com.clientserver.servlet.*"%>

<!-- Session for current user -->

<%
if (session.getAttribute("name") == null) {
	response.sendRedirect("login.jsp");
}
%>

<!-- Call the function for get all products -->

<%
ProductDao pd = new ProductDao();
List<Product> products = pd.getAllProducts();
%>


<!DOCTYPE html>
<html>
<head>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="D:\DCF\JSPServlet\tryJSP\src\main\webapp\css\productHome\productStyle.css">
<link rel="stylesheet" href="css/productStyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>PRODUCT DETAILS</title>

<body>
	<div class="topnav">
	<img  src="img/CompanyLogo.jpg" style="height:80px;width:90px;float: left;">
		<a  href="#home">Home</a> <a href="#news">News</a> 
		<a href="#contact">Contact</a> <a href="#about">About</a>
        
        <form action="Logout">
			<button
				style="background-color: #66b3ff; border: none; color: white; padding: 5px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 10px 10px; float: right; border-radius: 50%;">
				<i class="fa fa-sign-out"></i>
			</button>
			<a href="product.jsp" style="float: right;"><%=session.getAttribute("name")%></a>
		</form>

		<div style="float: right;">
				<a href="cart.jsp">Cart</a>
				<%-- <% 
     ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list"); %>
	
		<button style="float: right;border-radius: 50%;color: white;border:none;background-color:red; margin:13px 1px;">
		<%= cart_list.size() %></button>--%>
				</div> 
	</div>
	

	<div class="container">
		<div class="card-header my-3">All products</div>
		<div class="row">
<%
if(!products.isEmpty()){
	for(Product p:products){%><div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="img/<%= p.getImage() %>" style="height:250px;">
					<div class="card-body">
						<h6 class="card-title"><%= p.getPname() %></h6>
						<h7 class="price">Price: $<%= p.getAmount() %></h7>
						<div class="mt-3 d-flex justify-c0ntent-between">
						<a href="Cart?id=<%= p.getId_product() %>" class="btn btn-primary" style="background-color:#33bbff;border: none;">Add To Cart</a>
							 &#160;&#160;&#160;
							<a href="BuyNow?id=<%= p.getId_product() %>" class="btn btn-primary" style="background-color:#00cc00;border: none;">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
	<% }
}
%>	
		</div>
	</div>
</body>
</html>