package com.clientserver.dao;
import java.sql.*;
import java.util.*;
import com.clientserver.dto.*;
public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	static int qty=0;
	//List all produts
	
   public List<Product> getAllProducts(){
	   List<Product> products=new ArrayList<Product>();
	   try {
		   con = ConnectionUtil.getConnection();
		   query="select * from product";
		   ps=con.prepareStatement(query);
		   rs=ps.executeQuery();
		   while(rs.next()) {
			   Product row=new Product();
			   row.setId_product(rs.getInt("id_product"));
			   row.setPname(rs.getNString("pname"));
			   row.setQty(rs.getInt("qty"));
			   row.setAmount(rs.getInt("amount"));
			   row.setImage(rs.getString("image"));
			   products.add(row);
			   
			   
		   }
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return products;
   }
   
   //Add products to cart.
   
   public List<Cart> getCartProducts(ArrayList<Cart> cartList){
	   List<Cart> products=new ArrayList<Cart>();
	   try {
		   //The cart List  contains item.
		   if(cartList.size()>0) {
			   for(Cart item:cartList) {
				   con = ConnectionUtil.getConnection();
				   query="select * from product where Id_product=?";
				   ps=con.prepareStatement(query);
				  ps.setInt(1,item.getId_product());
				   rs=ps.executeQuery();
				   while(rs.next()) {
					   Cart row=new Cart();
					   row.setId_product(rs.getInt("Id_product"));
					   row.setPname(rs.getString("pname"));
					   row.setAmount(rs.getInt("amount")*item.getQuantity());
					 
						
					   
					   row.setQuantity(item.getQuantity());
					   products.add(row);
					  
				   }
			   }
		   }
		   
		   
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
		 // e.printStackTrace();
	   }
	   
	   return products;
	   
	   
	   
	   
   }
   
   public int getTotalCartPrice(ArrayList<Cart> cartList) {
	   int sum=0;
	   try {
		   if(cartList.size()>0) {
			   for(Cart item:cartList) {
				   con = ConnectionUtil.getConnection();
				   query="select amount from product where Id_product=?";
				   ps=con.prepareStatement(query);
				   ps.setInt(1, item.getId_product());
				   rs=ps.executeQuery();
				   while(rs.next()) {
					   sum+=rs.getDouble("amount")*item.getQuantity();
				   }
			   }
			   
			   
		   }
		   
	   }catch(Exception e) {
		   e.printStackTrace();
		   System.out.println(e.getMessage());
	   }
	   return sum;
   }
   
  
   
   
   
   
   
   
   
   
   
	

}
