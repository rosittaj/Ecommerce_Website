<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import=" com.clientserver.dto.*"%>
<%@ page import="com.clientserver.dao.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js" integrity="sha512-fzff82+8pzHnwA1mQ0dzz9/E0B+ZRizq08yZfya66INZBz86qKTCt9MLU0NCNIgaMJCgeyhujhasnFUsYMsi0Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>cart page</title>
</head>
<body>

<!-- Show all products in the cart -->

<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pd = new ProductDao();
	cartProduct = pd.getCartProducts(cart_list);
	int total = pd.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!-- Showing all cart items in a table -->

	<div class="container">
		<div class="card-header my-3">
			All products
			<%-- <button style="float: right;border-radius: 50%;color: white;border:none;background-color: red;"><%= cart_list.size() %></button> --%>
			<a class="active" href="product.jsp">
			<i class="fa fa-home fa-1.8x"></i></a>
		</div>
		<table class="table table-loght">
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				<th scope="col">Buy Now</th>
				<th scope="col">Cancel</th>

			</tr>
			<%      
			if(cart_list != null){
				for(Cart c:cartProduct){ %>
			<tr>
				<td><%= c.getPname() %></td>
				<td><%= c.getAmount() %></td>
				<td><form action="" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId_product() %>" class="form-input">
						<div class="form-group d-flex justify-content-between">
							<a class="btn btn-sm btn-decre" href="Quantity?action=dec&id=<%= c.getId_product() %>"> <i class="fas fa-minus-square"></i></a> 
							<input type="text" name="quantity" class="form-control" value="<%= c.getQuantity() %>" readonly> 
							<a class="btn btn-sm btn-incre" href="Quantity?action=inc&id=<%= c.getId_product() %>">
							<i class="fas fa-plus-square"></i></a>
                        </div>

					</form>
				</td>
				<td><a class="btn btn-sm btn-danger" href="Remove?id=<%= c.getId_product() %>">Remove</a></td>
	       </tr>
			<%}
			}%>
		</table>
		
		<!-- The total amount of all cart products -->
		
		<div class="container">
			<div class="d-flex py-3">
				<h4>Total Amount: $ ${ total }</h4>
				<a class="mx-3 btn btn-primary" href="Checkout?total=${total }&name=<%=session.getAttribute("name")%>">Check Out</a> 
				<a class="mx-3 btn btn-warning" href="Balance?total=${total }&name=<%=session.getAttribute("name")%>" style="float: right; color: white;">Check Balance</a>
			</div>
		</div>
	</div>
	
  <!--   Script for show success message after data stored in database -->
  
    <script>
        function alerting(){
	        swal("Success!", "Press OK to Sign In!", "success");
           }
    </script>

</body>
</html>