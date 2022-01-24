
package com.clientserver.dao;

import java.sql.*;


import com.clientserver.dto.User;

import com.clientserver.dto.Login;

public class DBUtil {
	static int rowCount;
	public static int saveUser(User user) {
		Connection con;
		try {
			con = ConnectionUtil.getConnection();
			
			String sql = "insert into registration(name,email,password,phno,country,amount) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhno());
			ps.setString(5, user.getCountry());
			ps.setString(6, user.getAmount());
			
			rowCount=ps.executeUpdate();
			ConnectionUtil.close(con);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.print(e);
		}
	return rowCount;
	}
		public static User loginUser(String email,String password) {
			Connection con;
			User user=null;
			try {
				con = ConnectionUtil.getConnection();
				
				// checking user email/password in the table
				
				String sql = "select * from registration where email=? and password=?";
				PreparedStatement ps = con.prepareStatement(sql);
			    ps.setString(1,email);
			    ps.setString(2,password);
			    ResultSet rs=ps.executeQuery();
			    if(rs.next()) {
			    	user=new User();
			    	user.setEmail(rs.getString("email"));
			    	user.setPassword(rs.getString("password"));
			    }
			    } catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return user;
	}
}

